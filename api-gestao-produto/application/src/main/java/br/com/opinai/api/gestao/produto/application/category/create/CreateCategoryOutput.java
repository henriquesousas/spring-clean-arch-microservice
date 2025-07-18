package br.com.opinai.api.gestao.produto.application.category.create;


import br.com.opinai.api.gestao.produto.domain.category.Category;

public record CreateCategoryOutput(
        String categoryId
) {


    public static CreateCategoryOutput from(String identifier) {
        return new CreateCategoryOutput(identifier);
    }

    public static CreateCategoryOutput from(Category category) {
        return new CreateCategoryOutput(category.getId().getValue());
    }
}
