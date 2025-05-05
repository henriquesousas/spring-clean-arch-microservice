package com.jobee.admin.service.infrastructure.category.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobee.admin.service.application.category.retrieve.GetCategoryOutput;
import com.jobee.admin.service.domain.pagination.Pagination;

import java.time.Instant;
import java.util.List;

public record PaginationCategoryResponse(
        @JsonProperty("current_page") int currentPage,
        @JsonProperty("per_page") int perPage,
        @JsonProperty("total") long total,
        @JsonProperty("items") List<GetCategoryResponse> items

) {

    static PaginationCategoryResponse from(Pagination<GetCategoryOutput> data) {
        List<GetCategoryResponse> items = data.items()
                .stream()
                .map(GetCategoryResponse::from)
                .toList();

        return new PaginationCategoryResponse(
                data.currentPage(),
                data.perPage(),
                data.total(),
                items
        );
    }
}
