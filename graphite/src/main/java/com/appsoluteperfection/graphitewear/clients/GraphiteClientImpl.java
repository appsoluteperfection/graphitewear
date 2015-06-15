package com.appsoluteperfection.graphitewear.clients;

import com.appsoluteperfection.graphitewear.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphitewear.dtos.GraphiteEntryDto;
import com.google.inject.Inject;

import java.util.Collection;

public class GraphiteClientImpl implements GraphiteClient {

    @Inject GraphiteSearchUrlBuilder _urlBuilder;
    @Inject JsonRestClient _restClient;

    public Collection<GraphiteEntryDto> getGraphsFrom(String searchString){
        String searchUrl = _urlBuilder.buildFrom(searchString);
        return _restClient.get(searchUrl, Collection.class);
    }

}