package com.appsoluteperfection.graphitewear.queries;

import com.appsoluteperfection.graphitewear.entities.Graph;

import java.util.Collection;

public interface HistoricalQueryCollection {

    void add(Graph graph);
    Collection<Graph> getAll();
    void clear();
}
