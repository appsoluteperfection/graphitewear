package com.appsoluteperfection.graphitewear.contexts;

import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NodeContext {
    private static String nodeId;

    public static String getNodeId(Context context){
        if (null == nodeId){
            retrieveDeviceNode(context);
        }
        return nodeId;
    }

    public static void setNodeId(String value){
        nodeId = value;
    }

    private static final int CONNECTION_TIME_OUT_MS = 7000;

    public static void retrieveDeviceNode(Context context) {
        final GoogleApiClient client = GoogleWearClientProvider.getClient(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                client.blockingConnect(CONNECTION_TIME_OUT_MS, TimeUnit.MILLISECONDS);
                NodeApi.GetConnectedNodesResult result =
                        Wearable.NodeApi.getConnectedNodes(client).await();
                List<Node> nodes = result.getNodes();
                if (nodes.size() > 0) {
                    nodeId = nodes.get(0).getId();
                }
                client.disconnect();
            }
        }).start();
    }
}
