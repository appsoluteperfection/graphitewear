package com.appsoluteperfection.graphitewear.clients;

import com.appsoluteperfection.graphitewear.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphitewear.dtos.GraphiteEntryCollectionDto;
import com.appsoluteperfection.graphitewear.dtos.GraphiteEntryDto;
import com.google.inject.Inject;

import java.util.Collection;
import java.util.LinkedList;

public class GraphiteClientImpl implements GraphiteClient {

    @Inject GraphiteSearchUrlBuilder _urlBuilder;
    @Inject JsonRestClient _restClient;

    public Collection<GraphiteEntryDto> getGraphsFrom(String searchString){
        String searchUrl = _urlBuilder.buildFrom(searchString);
        GraphiteEntryCollectionDto collectionDto =
                _restClient.get(searchUrl, GraphiteEntryCollectionDto.class);
//        Collection<GraphiteEntryDto> ret = new LinkedList<>();
//        GraphiteEntryDto[] items = collectionDto.getItems();
//        for (int i = 0; i < items.length; i++){
//            ret.add(items[i]);
//        }
        return collectionDto.getItems();
    }

}