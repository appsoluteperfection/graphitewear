package com.appsoluteperfection.graphite.bindings;

import android.app.Application;

import com.appsoluteperfection.graphite.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphite.builders.GraphiteSearchUrlBuilderImpl;
import com.appsoluteperfection.graphite.clients.GraphiteClient;
import com.appsoluteperfection.graphite.clients.GraphiteClientImpl;
import com.appsoluteperfection.graphite.clients.JsonRestClient;
import com.appsoluteperfection.graphite.clients.JsonRestClientImpl;
import com.appsoluteperfection.graphite.mocks.MockGraphiteQuery;
import com.appsoluteperfection.graphite.queries.GraphiteQuery;
import com.appsoluteperfection.graphite.queries.GraphiteQueryImpl;
import com.google.inject.AbstractModule;

public class SharedBindings extends AbstractModule {

    // TODO, move to some build setting
    private static boolean _isTesting = false;

    public SharedBindings(Application application) {
    }

    @Override
    protected void configure() {
        if (_isTesting) {
            bind(GraphiteQuery.class).to(MockGraphiteQuery.class);
        } else {
            bind(GraphiteSearchUrlBuilder.class).to(GraphiteSearchUrlBuilderImpl.class);
            bind(GraphiteClient.class).to(GraphiteClientImpl.class);
            bind(JsonRestClient.class).to(JsonRestClientImpl.class);
            bind(GraphiteQuery.class).to(GraphiteQueryImpl.class);
        }
    }
}
