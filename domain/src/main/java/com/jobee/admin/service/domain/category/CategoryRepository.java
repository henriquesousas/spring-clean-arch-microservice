package com.jobee.admin.service.domain.category;

import com.jobee.admin.service.domain.pagination.Pagination;

import java.util.Optional;


public interface CategoryRepository {
    Category create(Category category);
    Category update(Category category);
    void delete(CategoryId identifier);
    Optional<Category> findById(CategoryId identifier);
    Pagination<Category> findAll(CategorySearch query);
}
