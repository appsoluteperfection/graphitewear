package com.appsoluteperfection.graphitewear.dtos;

import com.appsoluteperfection.graphitewear.entities.Graph;

import java.util.Collection;

public class GraphWearResponse {

    private Collection<Graph> graphs;

    public Collection<Graph> getGraphs() {
        return graphs;
    }

    public void setGraphs(Collection<Graph> graphs) {
        this.graphs = graphs;
    }
}
