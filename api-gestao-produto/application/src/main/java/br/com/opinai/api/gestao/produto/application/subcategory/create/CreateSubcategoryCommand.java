package br.com.opinai.api.gestao.produto.application.subcategory.create;

import java.util.List;

public record CreateSubcategoryCommand(String name, List<String> categories) {

    public static CreateSubcategoryCommand with(
            final String name,
            final List<String> categories
    ) {
        return new CreateSubcategoryCommand(name, categories);
    }

}
