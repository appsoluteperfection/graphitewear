package com.appsoluteperfection.graphite.clients;

public interface JsonRestClient {

    public <T> T get(String url, Class<T> c);
}
