package com.appsoluteperfection.graphitewear.contexts;

import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;

public class GoogleWearClientProvider {
    public static GoogleApiClient getClient(Context context) {
        return new GoogleApiClient.Builder(context)
                .addApi(Wearable.API)
                .build();
    }
}
