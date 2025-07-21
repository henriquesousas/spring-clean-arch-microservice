<<<<<<<< HEAD:product-service/application/src/main/java/com/opinai/product/application/category/retrieve/ListCategoryUseCase.java
package com.opinai.product.application.category.retrieve;


import com.opinai.product.domain.category.CategoryRepository;
========
package br.com.opinai.api.gestao.produto.application.category.retrieve;


import br.com.opinai.api.gestao.produto.domain.category.CategoryRepository;
>>>>>>>> feat/product:api-gestao-produto/application/src/main/java/br/com/opinai/api/gestao/produto/application/category/retrieve/ListCategoryUseCase.java
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.pagination.Pagination;
import com.opinai.shared.domain.pagination.Search;

public class ListCategoryUseCase extends UseCase<Search, Pagination<CategoryOutput>> {

    private final CategoryRepository repository;

    public ListCategoryUseCase(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pagination<CategoryOutput> execute(Search categorySearch) {
        return this.repository
                .findAll(categorySearch)
                .map(CategoryOutput::from);

    }
}
