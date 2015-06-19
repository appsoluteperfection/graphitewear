package com.appsoluteperfection.graphitewear;

import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import roboguice.activity.RoboActivity;

public class MainActivity extends RoboActivity {

    private WebView webViewGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        webViewGraph = (WebView)stub.findViewById(R.id.graph);

        final String graphUrl = getIntent().getStringExtra("graphUrl");
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
