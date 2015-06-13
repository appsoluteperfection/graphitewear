package com.appsoluteperfection.graphite.mocks;

import com.appsoluteperfection.graphite.entities.Graph;
import com.appsoluteperfection.graphite.queries.GraphiteQuery;

import java.util.Collection;
import java.util.LinkedList;

public class MockGraphiteQuery implements GraphiteQuery {
    @Override
    public Collection<Graph> getGraphFromSearchString(String searchString) {
        LinkedList<Graph> graphs = new LinkedList<>();
        return graphs;
    }
}
