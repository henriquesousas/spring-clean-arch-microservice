package br.com.opinai.api.gestao.produto.application.category.update;

public record UpdateCategoryCommand(
        String id,
        String name
) {
}
