package com.appsoluteperfection.graphitewear.queries;

import com.appsoluteperfection.graphitewear.clients.GraphiteClient;
import com.appsoluteperfection.graphitewear.dtos.GraphiteEntryDto;
import com.appsoluteperfection.graphitewear.entities.Graph;
import com.google.inject.Inject;

import java.util.Collection;
import java.util.LinkedList;

public class GraphiteQueryImpl implements GraphiteQuery {

    @Inject GraphiteClient _graphiteClient;

    public Collection<Graph> getGraphFromSearchString(String searchString){
        Collection<GraphiteEntryDto> dtos = _graphiteClient.getGraphsFrom(searchString);
        Collection<Graph> graphs = new LinkedList<>();
        for (GraphiteEntryDto dto : dtos){
            graphs.add(new Graph(dto));
        }
        return graphs;
    }
}