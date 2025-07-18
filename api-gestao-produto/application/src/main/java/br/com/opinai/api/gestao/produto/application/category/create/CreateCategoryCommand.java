package br.com.opinai.api.gestao.produto.application.category.create;

public record CreateCategoryCommand(
        String name
) {


    public static CreateCategoryCommand with(
            final String name
    ) {
        return new CreateCategoryCommand(name);
    }
}
