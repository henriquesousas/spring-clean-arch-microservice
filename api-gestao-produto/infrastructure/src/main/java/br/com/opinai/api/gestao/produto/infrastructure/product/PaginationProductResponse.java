package br.com.opinai.api.gestao.produto.infrastructure.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.opinai.api.gestao.produto.application.product.usecase.ProductOutput;

import com.opinai.shared.domain.pagination.Pagination;

import java.util.List;

public record PaginationProductResponse(
        @JsonProperty("current_page") int currentPage,
        @JsonProperty("per_page") int perPage,
        @JsonProperty("total") long total,
        @JsonProperty("items") List<ProductOutput> items
) {

    public static PaginationProductResponse from(Pagination<ProductOutput> data) {
        List<ProductOutput> items = data.items()
                .stream()
                .toList();

        return new PaginationProductResponse(
                data.currentPage(),
                data.perPage(),
                data.total(),
                items
        );
    }
}
