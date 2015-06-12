package com.appsoluteperfection.graphite.queries;

import com.appsoluteperfection.graphite.clients.GraphiteClient;
import com.appsoluteperfection.graphite.dtos.GraphiteEntryDto;

import java.util.Collection;

public class GraphiteQueryImpl implements GraphiteQuery {

    private GraphiteClient _client;

    public GraphiteQueryImpl(GraphiteClient client){
        _client = client;
    }
    public int getGraphFromSearchString(String searchString){
        Collection<GraphiteEntryDto> results =_client.getGraphsFrom(searchString);

        return -1;
    }
}