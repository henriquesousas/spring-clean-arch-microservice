package com.jobee.admin.service.domain.category;

import com.jobee.admin.service.domain.pagination.Pagination;

import java.util.Optional;

//TODO: Renomear esta classe
//TODO: Renomear nomes dos metodos find para get
public interface CategoryRepositoryGateway {
    Category create(Category category);
    Category update(Category category);
    void delete(CategoryId identifier);
    Optional<Category> findById(CategoryId identifier);
    Pagination<Category> findAll(CategorySearch query);
}
