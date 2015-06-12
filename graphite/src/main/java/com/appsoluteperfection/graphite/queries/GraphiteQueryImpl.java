package com.appsoluteperfection.graphite.queries;

<<<<<<< HEAD
import com.appsoluteperfection.graphite.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphite.entities.Graph;

import org.apache.commons.lang.NotImplementedException;

import java.util.Collection;
import java.util.LinkedList;

public class GraphiteQueryImpl implements GraphiteQuery {

    private GraphiteSearchUrlBuilder urlBuilder;

    public GraphiteQueryImpl(GraphiteSearchUrlBuilder urlBuilder){
        this.urlBuilder = urlBuilder;
    }
    public Collection<Graph> getGraphFromSearchString(String searchString){
        if (null == searchString || "".equals(searchString.trim())) return new LinkedList<>();
        String url = urlBuilder.buildFrom(searchString);
        throw new NotImplementedException();
//        return new LinkedList<>();
=======
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
>>>>>>> 6a6d45527656a2cfc597b3015cb9c5e84adb2676
    }
}