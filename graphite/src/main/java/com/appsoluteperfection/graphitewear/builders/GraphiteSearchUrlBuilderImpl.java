package com.appsoluteperfection.graphitewear.builders;

import com.appsoluteperfection.graphitewear.configuration.ApplicationSettings;

import javax.inject.Inject;

public class GraphiteSearchUrlBuilderImpl implements GraphiteSearchUrlBuilder{

    private ApplicationSettings _settings;

    @Inject
    public GraphiteSearchUrlBuilderImpl(ApplicationSettings settings){
        _settings = settings;
    }

    private String getBaseUrl(){
        return _settings.getGraphiteServerBaseUrl();
    }

    public String buildFrom(String graphiteCollection){

        return appendBase(graphiteCollection);
    }

    private String appendBase(String s){
        return getBaseUrl() + "/graphs?q=" + s;
    }
}