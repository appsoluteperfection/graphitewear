package com.appsoluteperfection.graphitewear;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.appsoluteperfection.graphitewear.contexts.GoogleWearClientProvider;
import com.appsoluteperfection.graphitewear.contexts.NodeContext;
import com.appsoluteperfection.graphitewear.dtos.GraphRequestType;
import com.appsoluteperfection.graphitewear.dtos.GraphWearResponse;
import com.appsoluteperfection.graphitewear.entities.Graph;
import com.appsoluteperfection.graphitewear.serialization.JsonSerializer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import roboguice.activity.RoboActivity;

public class GraphListActivity extends RoboActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_list);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        listView = (ListView)stub.findViewById(R.id.list);
        final String graphResponse = getIntent().getStringExtra("graphResponse");
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                setList(graphResponse);
            }
        });
    }

    private final int CONNECTION_TIME_OUT_MS = 7000;

    public void sendMessage(Context context, GraphRequestType requestType) {
        final String requestTypeString = requestType.toString();
        final GoogleApiClient client = GoogleWearClientProvider.getClient(context);
        final String nodeId = NodeContext.getNodeId(context);
        if (nodeId != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    client.blockingConnect(CONNECTION_TIME_OUT_MS, TimeUnit.MILLISECONDS);
                    Wearable.MessageApi.sendMessage(client, nodeId, requestTypeString, null);
                    client.disconnect();
                }
            }).start();
        }
        else{
            NodeContext.retrieveDeviceNode(context);
        }
    }

    private void setList(String graphResponse){
        if (null == graphResponse || "".equals(graphResponse)){
            setList();
        }
        else{
            GraphWearResponse graphWearResponse = JsonSerializer.deserialize(graphResponse, GraphWearResponse.class);
            Collection<Graph> graphCollection = graphWearResponse.getGraphs();
            Graph [] graphs = graphCollection.toArray(new Graph[graphCollection.size()]);
            setList(graphs);
        }
    }

    private void setList(){
        final Activity activity = this;
        String [] items = new String []{ "All Graphs", "Historical Graphs" };
        listView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (0 == position){
                    sendMessage(activity, GraphRequestType.AllGraphs);
                }
                else if (1 == position){
                    sendMessage(activity, GraphRequestType.History);
                }
            }
        });
    }

    private void setList(final Graph[] graphs){
        final Activity activity = this;
        listView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, graphs));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                setGraph(position);
            }
            private void setGraph(int position) {
                Intent intent = new Intent(activity, MainActivity.class);
                Graph graph = graphs[position];
                String imageUrl = graph.getImageUrl();
                intent.putExtra("graphUrl", imageUrl);
                activity.startActivity(intent);
            }
        });
    }


}
