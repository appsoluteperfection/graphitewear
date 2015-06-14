package com.appsoluteperfection.graphite.queries;

import com.appsoluteperfection.graphite.clients.GraphiteClient;
import com.appsoluteperfection.graphite.dtos.GraphiteEntryDto;
import com.appsoluteperfection.graphite.entities.Graph;

import java.util.Collection;
import java.util.LinkedList;

public class GraphiteQueryImpl implements GraphiteQuery {

    private final GraphiteClient _graphiteClient;

    public GraphiteQueryImpl(GraphiteClient graphiteClient){
        _graphiteClient = graphiteClient;
    }

    public Collection<Graph> getGraphFromSearchString(String searchString){
        Collection<GraphiteEntryDto> dtos = _graphiteClient.getGraphsFrom(searchString);
        Collection<Graph> graphs = new LinkedList<>();
        for (GraphiteEntryDto dto : dtos){
            graphs.add(new Graph(dto));
        }
        return graphs;
    }
}