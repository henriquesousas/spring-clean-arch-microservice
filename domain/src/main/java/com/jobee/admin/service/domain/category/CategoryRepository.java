package com.jobee.admin.service.domain.category;

import com.jobee.admin.service.domain.commons.pagination.Search;
import com.jobee.admin.service.domain.commons.pagination.Pagination;

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
