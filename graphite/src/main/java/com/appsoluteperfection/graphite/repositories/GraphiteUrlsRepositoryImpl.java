package com.appsoluteperfection.graphite.repositories;

import com.appsoluteperfection.graphite.builders.GraphiteSearchUrlBuilder;
import com.appsoluteperfection.graphite.clients.GraphiteClientImpl;
import com.appsoluteperfection.graphite.clients.JsonRestClient;
import com.appsoluteperfection.graphite.dtos.GraphiteEntryDto;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by cvernino on 6/12/2015.
 */
public class GraphiteUrlsRepositoryImpl implements GraphiteUrlsRepository{

    private Collection<GraphiteEntryDto> _graphiteUrls = null;
    private GraphiteSearchUrlBuilder _urlBuilder;
    private GraphiteClientImpl graphiteClient;

    public GraphiteUrlsRepositoryImpl(GraphiteSearchUrlBuilder urlBuilder, GraphiteClientImpl graphiteClient) {
        _urlBuilder = urlBuilder;
        this.graphiteClient = graphiteClient;
    }


    @Override
    public Collection<GraphiteEntryDto> getAll() {
        if(_graphiteUrls == null){
            calculateUrls();
        }

        return _graphiteUrls;
    }

    private Collection<String> calculateUrls() {
        searchHelper(_urlBuilder.buildFrom("stats"));

        _graphiteUrls = new ArrayList<>();

        return null;
    }

    private void searchHelper(String currentNode){
        Collection<GraphiteEntryDto> childNodes = graphiteClient.getGraphsFrom(currentNode);
        for(GraphiteEntryDto entry : childNodes){
            if(entry.Leaf == 1){
                _graphiteUrls.add(entry);
            }else{
                searchHelper(entry.Id);
            }
        }
    }


}
