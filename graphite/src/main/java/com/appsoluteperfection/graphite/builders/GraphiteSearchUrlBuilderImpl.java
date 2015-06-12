package com.appsoluteperfection.graphite.builders;

public class GraphiteSearchUrlBuilderImpl implements GraphiteSearchUrlBuilder{
    private static String baseUrl = "http://graphite.local.uship.com/metrics/json/find?query=";
    // example query: http://graphite.local.uship.com/metrics/json/find?query=stats.*
    public String buildFrom(String graphiteCollection){
        return appendBase(graphiteCollection);
    }

    private String appendBase(String s){
        return baseUrl + s + ".*";
    }
}