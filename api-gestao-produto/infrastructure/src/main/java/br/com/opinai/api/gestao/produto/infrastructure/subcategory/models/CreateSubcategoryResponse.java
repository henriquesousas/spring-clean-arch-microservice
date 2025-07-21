package br.com.opinai.api.gestao.produto.infrastructure.subcategory.models;

public record CreateSubcategoryResponse(String id) {
    public static CreateSubcategoryResponse with(final String id) {
        return new CreateSubcategoryResponse(id);
    }
}
