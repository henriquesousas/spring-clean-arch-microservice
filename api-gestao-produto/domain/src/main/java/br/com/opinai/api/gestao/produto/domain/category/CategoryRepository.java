<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/CategoryRepository.java
package com.opinai.product.domain.category;
========
package br.com.opinai.api.gestao.produto.domain.category;
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/CategoryRepository.java


import com.opinai.shared.domain.pagination.Pagination;
import com.opinai.shared.domain.pagination.Search;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository {
    Category create(Category category);
    Category update(Category category);
    void delete(CategoryId identifier);
    Optional<Category> findById(CategoryId identifier);
    Pagination<Category> findAll(Search query);
    List<CategoryId> existByIds(Iterable<CategoryId> ids);
}
