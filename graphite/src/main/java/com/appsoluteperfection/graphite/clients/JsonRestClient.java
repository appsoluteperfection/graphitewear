package com.appsoluteperfection.graphite.clients;

/**
 * Created by cvernino on 6/12/2015.
 */
public interface JsonRestClient {

    public <T> T get(String url);
}
