package com.appsoluteperfection.graphitemobile;

import android.app.Application;

import roboguice.RoboGuice;
import roboguice.util.Ln;

@SuppressWarnings("unused")
public class CustomApplication extends Application {
    public void onCreate() {
        super.onCreate();
        RoboGuice.setUseAnnotationDatabases(false);
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                // Replace the following line with your own handling code
                Ln.e(e);
            }
        });
    }
}
