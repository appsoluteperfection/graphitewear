package com.appsoluteperfection.graphite.bindings;

import android.app.Application;

import com.appsoluteperfection.graphite.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphite.builders.GraphiteSearchUrlBuilderImpl;
import com.appsoluteperfection.graphite.clients.GraphiteClient;
import com.appsoluteperfection.graphite.clients.GraphiteClientImpl;
import com.appsoluteperfection.graphite.clients.JsonRestClient;
import com.appsoluteperfection.graphite.clients.JsonRestClientImpl;
import com.appsoluteperfection.graphite.queries.GraphiteQuery;
import com.appsoluteperfection.graphite.queries.GraphiteQueryImpl;
import com.google.inject.AbstractModule;

public class SharedBindings extends AbstractModule {

    public SharedBindings(Application application) {
    }

    @Override
    protected void configure() {
        bind(GraphiteSearchUrlBuilder.class).to(GraphiteSearchUrlBuilderImpl.class);
        bind(GraphiteClient.class).to(GraphiteClientImpl.class);
        bind(JsonRestClient.class).to(JsonRestClientImpl.class);
        bind(GraphiteQuery.class).to(GraphiteQueryImpl.class);
    }
}
