package com.appsoluteperfection.graphitewear.serialization;

import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonSerializer {

    public static <T> T deserialize(String json, Class<T> c) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // TODO, what the hell, no content
        if (null == json) return null;
        try {
            return objectMapper.readValue(json, c);
        } catch (IOException e) {
            // TODO, handle
            Log.e("deserialize", "Error deserializing", e);
            return null;
        }
    }

    public static <T> String serialize(T object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            // TODO, prolly more
            Log.e("serialize", "Error serializing", e);
        }
        return null;
    }
}
