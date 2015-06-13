package com.appsoluteperfection.graphite.builders;

import com.appsoluteperfection.graphite.configuration.ApplicationSettings;

public class GraphiteSearchUrlBuilderImpl implements GraphiteSearchUrlBuilder{
    private static String baseUrl = "http://graphite.local.uship.com/metrics/json/find?query=";
    // example query: http://graphite.local.uship.com/metrics/json/find?query=stats.*

    public GraphiteSearchUrlBuilderImpl(ApplicationSettings settings)
    {
        // TODO, test this from the dependency instead
        // baseUrl = settings.getGraphiteServerBaseUrl();
    }

    public String buildFrom(String graphiteCollection){

        return appendBase(graphiteCollection);
    }

    private String appendBase(String s){
        return baseUrl + s + ".*";
    }
}