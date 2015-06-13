package com.appsoluteperfection.graphite.queries;

import com.appsoluteperfection.graphite.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphite.entities.Graph;

import org.apache.commons.lang.NotImplementedException;

import java.util.Collection;
import java.util.LinkedList;

public class GraphiteQueryImpl implements GraphiteQuery {

    private GraphiteSearchUrlBuilder _urlBuilder;

    public GraphiteQueryImpl(GraphiteSearchUrlBuilder urlBuilder){
        this._urlBuilder = urlBuilder;
    }
    public Collection<Graph> getGraphFromSearchString(String searchString){
        if (null == searchString || "".equals(searchString.trim())) return new LinkedList<>();
        String url = _urlBuilder.buildFrom(searchString);
        return new LinkedList<>();
    }
}