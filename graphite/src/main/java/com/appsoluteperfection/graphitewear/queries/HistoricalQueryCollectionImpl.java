package com.appsoluteperfection.graphitewear.queries;

import com.appsoluteperfection.graphitewear.entities.Graph;

import java.util.Collection;
import java.util.HashSet;

public class HistoricalQueryCollectionImpl implements HistoricalQueryCollection {

    private HashSet<Graph> _previousQueries = new HashSet<>();

    // TODO, store this in sql lite locally

    @Override
    public void add(Graph graph) {
        if (!_previousQueries.contains(graph)) {
            _previousQueries.add(graph);
        }
    }

    @Override
    public Collection<Graph> getAll() {
        return _previousQueries;
    }

    @Override
    public void clear() {
        _previousQueries.clear();
    }
}
