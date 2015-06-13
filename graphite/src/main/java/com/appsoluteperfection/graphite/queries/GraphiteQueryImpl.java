package com.appsoluteperfection.graphite.queries;

import com.appsoluteperfection.graphite.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphite.entities.Graph;

import org.apache.commons.lang.NotImplementedException;

import java.util.Collection;
import java.util.LinkedList;

public class GraphiteQueryImpl implements GraphiteQuery {

    private GraphiteSearchUrlBuilder urlBuilder;

    public GraphiteQueryImpl(GraphiteSearchUrlBuilder urlBuilder){
        this.urlBuilder = urlBuilder;
    }
    public Collection<Graph> getGraphFromSearchString(String searchString){
        if (null == searchString || "".equals(searchString.trim())) return new LinkedList<>();
        String url = urlBuilder.buildFrom(searchString);
        return new LinkedList<>();
    }
}