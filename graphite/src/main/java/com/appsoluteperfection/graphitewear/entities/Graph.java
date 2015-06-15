package com.appsoluteperfection.graphitewear.entities;

import com.appsoluteperfection.graphitewear.dtos.GraphiteEntryDto;

public class Graph {
    private String id;
    private String title;
    private String imageUrl;

    public Graph(){}

    public Graph(GraphiteEntryDto dto){
        if (null == dto) throw new IllegalArgumentException("dto to create Graph cannot be null");
        id = dto.getId();
        title = dto.getTitle();
        imageUrl = dto.getUrl();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
