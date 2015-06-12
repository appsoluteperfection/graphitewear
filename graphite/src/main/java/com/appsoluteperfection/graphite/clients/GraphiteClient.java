package com.appsoluteperfection.graphite.clients;

import com.appsoluteperfection.graphite.dtos.GraphDto;

import java.util.Collection;

public interface GraphiteClient {
    public Collection<GraphDto> getGraphsFrom(String searchString);
}
