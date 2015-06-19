package com.appsoluteperfection.graphitewear;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class MainActivity extends RoboActivity {

    @InjectView(R.id.graph) WebView webViewGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity activity = this;
        final String graphUrl = getIntent().getStringExtra("graphUrl");
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                webViewGraph.getSettings().setJavaScriptEnabled(true);
                webViewGraph.getSettings().setPluginState(WebSettings.PluginState.ON);
                webViewGraph.setWebViewClient(new WebViewClient());
                webViewGraph.loadUrl(graphUrl);
            }
        });
    }
}
