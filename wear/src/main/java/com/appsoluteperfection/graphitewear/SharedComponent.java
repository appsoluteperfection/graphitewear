package com.appsoluteperfection.graphitewear;

import android.app.Application;

import com.appsoluteperfection.graphitewear.clients.GraphiteClient;
import com.appsoluteperfection.graphitewear.clients.JsonRestClient;
import com.appsoluteperfection.graphitewear.configuration.ApplicationSettings;
import com.appsoluteperfection.graphitewear.queries.GraphiteQuery;
import com.appsoluteperfection.graphitewear.queries.HistoricalQueryCollection;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SharedModule.class})
public interface SharedComponent {

    Application application();

    void inject(MainActivity activity);
    void inject(MainActivity.PlaceholderFragment fragment);

    GraphiteQuery provideGraphiteQuery();

    GraphiteClient provideGraphiteClient();

    JsonRestClient provideJsonRestClient();

    HistoricalQueryCollection provideHistoricalQueryCollection();

    ApplicationSettings provideApplicationSettings();

}
