package com.appsoluteperfection.graphitewear.clients;

import android.os.Message;
import android.util.Pair;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

import javax.net.ssl.SSLException;

import roboguice.util.Ln;

public class JsonRestClientImpl implements JsonRestClient {
    @Override
    public <T> T get(String url, Class<T> c) {
        Pair<Integer, String> results;
        try{
            results = getWebServiceResult(url);
        }
        catch (Exception exception){
            // TODO, probably handle edge cases here better
            Ln.e(exception, "Unable to retrieve json result");
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // TODO check for errors
        if (null == results) return null;
        // TODO handle different status codes
//        if (200 != results.first) return null;
        String json = results.second;
        // TODO, what the hell, no content
        if (null == json) return null;
        try {
            return objectMapper.readValue(json, c);
        } catch (IOException e) {
            // TODO, handle
            Ln.e(e);
            return null;
        }
    }

    protected Pair<Integer, String> getWebServiceResult(final String url) throws Exception {
        try {
            final Message msg = new Message();
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Pair<Integer, String> returnValue;
                        HttpClient client = new DefaultHttpClient();
                        HttpGet request = new HttpGet(url);
                        URI uri = new URI(url);
                        request.setURI(uri);

                        HttpResponse response = client.execute(request);
                        String content = EntityUtils.toString(response.getEntity(),
                                "UTF-8");
                        int statusCode = response.getStatusLine().getStatusCode();
                        returnValue = new Pair<>(statusCode, content);
                        msg.obj = returnValue;
                    } catch (Exception e) {
                        msg.obj = e;
                    }
                }
            });
            thread.start();
            thread.join();
            if (msg.obj instanceof Exception)
                throw (Exception) msg.obj;
            return msg.obj == null ? null : (Pair<Integer, String>)msg.obj;
        } catch (SSLException e) {
            return getWebServiceResult(url.replace("https://", "http://"));
        }
    }
}
