package com.appsoluteperfection.graphitewear.mocks;

import com.appsoluteperfection.graphitewear.entities.Graph;
import com.appsoluteperfection.graphitewear.queries.GraphiteQuery;

import java.util.Collection;
import java.util.LinkedList;

public class MockGraphiteQuery implements GraphiteQuery {
    @Override
    public Collection<Graph> getGraphFromSearchString(String searchString) {
        LinkedList<Graph> graphs = new LinkedList<>();
        return graphs;
    }
}
