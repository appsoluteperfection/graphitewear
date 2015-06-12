package com.appsoluteperfection.graphite.builders;

public class GraphiteSearchUrlBuilderImpl implements GraphiteSearchUrlBuilder{
    private static String baseUrl = "http://graphite.local.uship.com/";
    public String buildFrom(String graphiteCollection){
        return "http://graphite.local.uship.com/render?target=stats.timers.RabbitMessageReceived.BidCreatedEvent.count";
    }
}