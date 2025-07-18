package br.com.opinai.api.gestao.produto.application.category.update;


import br.com.opinai.api.gestao.produto.domain.category.Category;

public record UpdateCategoryOutputDto(String id) {
    public static UpdateCategoryOutputDto from(Category category) {
        return new UpdateCategoryOutputDto(category.getId().getValue());
    }
}
