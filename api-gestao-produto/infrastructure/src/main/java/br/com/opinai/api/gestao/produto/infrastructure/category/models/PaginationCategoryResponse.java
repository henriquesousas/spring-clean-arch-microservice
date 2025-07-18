package br.com.opinai.api.gestao.produto.infrastructure.category.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.opinai.api.gestao.produto.application.category.retrieve.CategoryOutput;
import com.opinai.shared.domain.pagination.Pagination;

import java.util.List;

public record PaginationCategoryResponse(
        @JsonProperty("current_page") int currentPage,
        @JsonProperty("per_page") int perPage,
        @JsonProperty("total") long total,
        @JsonProperty("items") List<CategoryResponse> items

) {

    public static PaginationCategoryResponse from(Pagination<CategoryOutput> data) {
        List<CategoryResponse> items = data.items()
                .stream()
                .map(CategoryResponse::from)
                .toList();

        return new PaginationCategoryResponse(
                data.currentPage(),
                data.perPage(),
                data.total(),
                items
        );
    }
}
