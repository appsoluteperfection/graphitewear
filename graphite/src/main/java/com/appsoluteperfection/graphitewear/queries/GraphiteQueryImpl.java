package com.appsoluteperfection.graphitewear.queries;

import com.appsoluteperfection.graphitewear.clients.GraphiteClient;
import com.appsoluteperfection.graphitewear.dtos.GraphiteEntryDto;
import com.appsoluteperfection.graphitewear.entities.Graph;
import com.google.inject.Inject;

import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GraphiteQueryImpl implements GraphiteQuery {

    @Inject GraphiteClient _graphiteClient;

    @Override
    public Collection<Graph> getAll() {
        return getGraphFromSearchString("");
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