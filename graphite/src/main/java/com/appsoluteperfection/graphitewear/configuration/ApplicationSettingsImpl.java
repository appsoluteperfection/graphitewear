package com.appsoluteperfection.graphitewear.configuration;

public class ApplicationSettingsImpl implements ApplicationSettings {

    private String _graphiteServerBaseUrl;

    public ApplicationSettingsImpl(String graphiteServerBaseUrl) {
        _graphiteServerBaseUrl = graphiteServerBaseUrl;
    }

    @Override
    public String getGraphiteServerBaseUrl() {
        return _graphiteServerBaseUrl;
    }

    @Override
    public void setGraphiteServerBaseUrl(String value) {
        _graphiteServerBaseUrl = value;
    }
}
