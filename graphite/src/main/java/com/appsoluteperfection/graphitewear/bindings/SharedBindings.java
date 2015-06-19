package com.appsoluteperfection.graphitewear.bindings;

import android.app.Application;

import com.appsoluteperfection.graphitewear.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphitewear.builders.GraphiteSearchUrlBuilderImpl;
import com.appsoluteperfection.graphitewear.clients.GraphiteClient;
import com.appsoluteperfection.graphitewear.clients.GraphiteClientImpl;
import com.appsoluteperfection.graphitewear.clients.JsonRestClient;
import com.appsoluteperfection.graphitewear.clients.JsonRestClientImpl;
import com.appsoluteperfection.graphitewear.configuration.ApplicationSettings;
import com.appsoluteperfection.graphitewear.configuration.ApplicationSettingsImpl;
import com.appsoluteperfection.graphitewear.mocks.MockGraphiteQuery;
import com.appsoluteperfection.graphitewear.queries.GraphiteQuery;
import com.appsoluteperfection.graphitewear.queries.GraphiteQueryImpl;
import com.appsoluteperfection.graphitewear.queries.HistoricalQueryCollection;
import com.appsoluteperfection.graphitewear.queries.HistoricalQueryCollectionImpl;
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
            bind(HistoricalQueryCollection.class)
                    .toInstance(HistoricalQueryCollectionImpl.getInstance());
            // TODO, move this to preferences for the phone
            // I plan on using sguerra.uship for other purposes, using Andy since it is not used
            bind(ApplicationSettings.class)
                    .toInstance(new ApplicationSettingsImpl("http://andy.uship.com/api"));
        }
    }
}
