package com.appsoluteperfection.graphitewear;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class SharedApplication extends Application {

    private SharedComponent _component;

    public static SharedComponent getComponent(Context context) {
        return ((SharedApplication) context.getApplicationContext())._component;
    }

    public void onCreate() {
        super.onCreate();

//        _component = DaggerSharedComponent.builder()
//                .sharedModule(new SharedModule(this))
//                .build();

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                // Replace the following line with your own handling code
                Log.e("fatal", "Application Exception Occurred", e);
            }
        });
    }

}
