package com.appsoluteperfection.graphite.queries;

import com.appsoluteperfection.graphite.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphite.entities.Graph;

import java.util.Collection;

public interface GraphiteQuery{

    public Collection<Graph> getGraphFromSearchString(String searchString);
}