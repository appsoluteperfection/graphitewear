package com.appsoluteperfection.graphite.clients;

import com.appsoluteperfection.graphite.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphite.dtos.GraphDto;
import com.appsoluteperfection.graphite.dtos.GraphiteEntryDto;

import java.util.Collection;

public class GraphiteClientImpl implements GraphiteClient {

    private GraphiteSearchUrlBuilder _urlBuilder;
    private JsonRestClient _restClient;

    GraphiteClientImpl(GraphiteSearchUrlBuilder urlBuilder){
        _urlBuilder = urlBuilder;
    }

    public Collection<GraphiteEntryDto> getGraphsFrom(String searchString){
        String searchUrl = _urlBuilder.buildFrom(searchString);
        return _restClient.get(searchUrl, Collection.class);
    }

}