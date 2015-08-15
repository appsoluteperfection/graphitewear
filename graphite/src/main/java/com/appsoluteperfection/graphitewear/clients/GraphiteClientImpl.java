package com.appsoluteperfection.graphitewear.clients;

import com.appsoluteperfection.graphitewear.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphitewear.dtos.GraphiteEntryCollectionDto;
import com.appsoluteperfection.graphitewear.dtos.GraphiteEntryDto;

import java.util.Collection;

import javax.inject.Inject;

public class GraphiteClientImpl implements GraphiteClient {

    private GraphiteSearchUrlBuilder _urlBuilder;
    private JsonRestClient _restClient;

    @Inject
    public GraphiteClientImpl(GraphiteSearchUrlBuilder urlBuilder,
                              JsonRestClient restClient){

        _urlBuilder = urlBuilder;
        _restClient = restClient;
    }

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