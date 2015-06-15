package com.appsoluteperfection.graphitewear.clients;

import com.appsoluteperfection.graphitewear.dtos.GraphiteEntryDto;

import java.util.Collection;

public interface GraphiteClient {
    public Collection<GraphiteEntryDto> getGraphsFrom(String searchString);
}
