package com.opinai.product.domain.category;


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
