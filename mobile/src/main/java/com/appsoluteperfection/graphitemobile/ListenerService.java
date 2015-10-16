package com.appsoluteperfection.graphitemobile;

import android.content.Context;

import com.appsoluteperfection.graphitewear.contexts.GoogleWearClientProvider;
import com.appsoluteperfection.graphitewear.contexts.NodeContext;
import com.appsoluteperfection.graphitewear.dtos.GraphRequestType;
import com.appsoluteperfection.graphitewear.dtos.GraphWearResponse;
import com.appsoluteperfection.graphitewear.entities.Graph;
import com.appsoluteperfection.graphitewear.queries.GraphiteQuery;
import com.appsoluteperfection.graphitewear.queries.HistoricalQueryCollection;
import com.appsoluteperfection.graphitewear.serialization.JsonSerializer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;


public class ListenerService extends WearableListenerService {

    private HistoricalQueryCollection _historicalQueryCollection;
    private GraphiteQuery _graphiteQuery;

    @Inject
    public ListenerService(HistoricalQueryCollection historicalQueryCollection,
                           GraphiteQuery graphiteQuery){
        this._historicalQueryCollection = historicalQueryCollection;
        this._graphiteQuery = graphiteQuery;
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        NodeContext.setNodeId(messageEvent.getSourceNodeId());
        String messageGiven = messageEvent.getPath();
        GraphRequestType requestType = GraphRequestType.valueOf(messageGiven);
        Collection<Graph> graphs = null;
        if (GraphRequestType.AllGraphs == requestType){
            graphs = _graphiteQuery.getGraphFromSearchString("");
        } else if (GraphRequestType.History == requestType){
            graphs = _historicalQueryCollection.getAll();
        }
        sendMessage(getBaseContext(), graphs);
    }

    private final int CONNECTION_TIME_OUT_MS = 7000;

    public void sendMessage(Context context, Collection<Graph> graphs) {
        final GraphWearResponse responseDto = new GraphWearResponse();
        responseDto.setGraphs(graphs);
        final String serialized = JsonSerializer.serialize(responseDto);

        final GoogleApiClient client = GoogleWearClientProvider.getClient(context);
        final String nodeId = NodeContext.getNodeId(context);
        if (nodeId != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    client.blockingConnect(CONNECTION_TIME_OUT_MS, TimeUnit.MILLISECONDS);
                    Wearable.MessageApi.sendMessage(client, nodeId, serialized, null);
                    client.disconnect();
                }
            }).start();
        }
        else{
            NodeContext.retrieveDeviceNode(context);
        }
    }


}
