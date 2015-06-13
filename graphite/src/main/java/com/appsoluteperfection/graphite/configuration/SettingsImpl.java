package com.appsoluteperfection.graphite.configuration;

public class SettingsImpl implements Settings {

    private String _graphiteServerBaseUrl;

    public SettingsImpl(String graphiteServerBaseUrl) {
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
