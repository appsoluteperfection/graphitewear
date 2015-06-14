package com.appsoluteperfection.graphite.builders;

import com.appsoluteperfection.graphite.configuration.ApplicationSettings;

public class GraphiteSearchUrlBuilderImpl implements GraphiteSearchUrlBuilder{

    private static String baseUrl;

    public GraphiteSearchUrlBuilderImpl(ApplicationSettings settings)
    {
        baseUrl = settings.getGraphiteServerBaseUrl();
    }

    public String buildFrom(String graphiteCollection){

        return appendBase(graphiteCollection);
    }

    private String appendBase(String s){
        return baseUrl + "/graphs?q=" + s;
    }
}