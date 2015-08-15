package com.appsoluteperfection.graphitewear;

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

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedModule {

    final SharedApplication _application;

    public SharedModule(SharedApplication application) {
        this._application = application;
    }

    @Provides @Singleton
    SharedApplication provideSharedApplication() {
        return _application;
    }

    @Provides @Singleton
    Application provideApplication(SharedApplication application) {
        return _application;
    }

    // TODO, move to some build setting
    private static boolean _isTesting = false;

    @Provides
    GraphiteSearchUrlBuilder provideGraphiteSearchUrlBuilder() {
        return new GraphiteSearchUrlBuilderImpl(provideApplicationSettings());
    }

    @Provides
    GraphiteQuery provideGraphiteQuery(GraphiteClient graphiteClient) {
        if (_isTesting) {
            return new MockGraphiteQuery();
        }
        return new GraphiteQueryImpl(graphiteClient);
    }

    @Provides
    GraphiteClient provideGraphiteClient(GraphiteSearchUrlBuilder urlBuilder,
                                         JsonRestClient restClient) {
        return new GraphiteClientImpl(urlBuilder, restClient);
    }

    @Provides
    JsonRestClient provideJsonRestClient() {
        return new JsonRestClientImpl();
    }

    @Provides
    HistoricalQueryCollection provideHistoricalQueryCollection() {
        return HistoricalQueryCollectionImpl.getInstance();
    }

    @Provides
    ApplicationSettings provideApplicationSettings() {
        return new ApplicationSettingsImpl("http://andy.uship.com/api");
    }

}
