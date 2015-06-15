package com.appsoluteperfection.graphitewear.queries;

import com.appsoluteperfection.graphitewear.entities.Graph;

import java.util.Collection;

public interface GraphiteQuery{

    public Collection<Graph> getGraphFromSearchString(String searchString);
}