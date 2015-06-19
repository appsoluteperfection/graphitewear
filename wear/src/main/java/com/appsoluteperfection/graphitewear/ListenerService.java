package com.appsoluteperfection.graphitewear;

import android.content.Intent;

import com.appsoluteperfection.graphitewear.contexts.NodeContext;
import com.appsoluteperfection.graphitewear.dtos.GraphWearResponse;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

public class ListenerService extends WearableListenerService {

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        NodeContext.setNodeId(messageEvent.getSourceNodeId());
        String messageGiven = messageEvent.getPath();
        Intent intent = new Intent(getBaseContext(), GraphListActivity.class);
        intent.putExtra("graphResponse", messageGiven);
        startActivity(intent);
    }

}
