package com.appsoluteperfection.graphitewear.builders;

import com.appsoluteperfection.graphitewear.configuration.ApplicationSettings;
import com.google.inject.Inject;

public class GraphiteSearchUrlBuilderImpl implements GraphiteSearchUrlBuilder{

    @Inject
    ApplicationSettings _settings;
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