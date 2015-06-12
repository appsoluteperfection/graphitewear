package com.appsoluteperfection.graphite.repositories;

import com.appsoluteperfection.graphite.dtos.GraphiteEntryDto;

import java.util.Collection;

/**
 * Created by cvernino on 6/12/2015.
 */
public interface GraphiteUrlsRepository {

    public Collection<GraphiteEntryDto> getAll();
}
