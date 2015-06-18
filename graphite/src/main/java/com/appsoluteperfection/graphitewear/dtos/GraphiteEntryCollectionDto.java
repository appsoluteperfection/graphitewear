package com.appsoluteperfection.graphitewear.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class GraphiteEntryCollectionDto {
    // TODO, dammit, API make it lowercase json proper java
    public Collection<GraphiteEntryDto> items;

    public Collection<GraphiteEntryDto> getItems(){
        return items;
    }

    public void setItems(Collection<GraphiteEntryDto> items){
        this.items = items;
    }
}
