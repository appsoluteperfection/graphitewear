package com.appsoluteperfection.graphite.clients;

import com.appsoluteperfection.graphite.dtos.GraphDto;
import com.appsoluteperfection.graphite.dtos.GraphiteEntryDto;

import java.util.Collection;

public interface GraphiteClient {
    public Collection<GraphiteEntryDto> getGraphsFrom(String searchString);
}
