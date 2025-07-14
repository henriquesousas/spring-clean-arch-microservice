package com.opinai.review.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListResponse<T> extends ApiResponse<List<T>> {

    @JsonProperty("items")
    private List<T> items;

    @JsonProperty("total")
    private int total;

    private ListResponse(List<T> items) {
        this.items = items;
        this.total = items.size();
    }

    public static <T> ListResponse<T> from(List<T> items) {
        return new ListResponse(items);
    }
}