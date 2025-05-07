package com.jobee.admin.service.application.category.retrieve;

import com.jobee.admin.service.application.UseCase;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.category.CategorySearch;
import com.jobee.admin.service.domain.pagination.Pagination;

public class ListCategoryUseCase extends UseCase<CategorySearch, Pagination<CategoryOutput>> {

    private final CategoryRepository repository;

    public ListCategoryUseCase(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pagination<CategoryOutput> execute(CategorySearch categorySearch) {
        return this.repository
                .findAll(categorySearch)
                .map(CategoryOutput::from);

    }
}
