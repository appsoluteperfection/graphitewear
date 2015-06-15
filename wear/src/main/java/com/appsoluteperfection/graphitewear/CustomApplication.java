package com.appsoluteperfection.graphitewear;

import android.app.Application;

import roboguice.RoboGuice;

@SuppressWarnings("unused")
public class CustomApplication extends Application {
    public void onCreate() {
        super.onCreate();
        RoboGuice.setUseAnnotationDatabases(false);
    }
}