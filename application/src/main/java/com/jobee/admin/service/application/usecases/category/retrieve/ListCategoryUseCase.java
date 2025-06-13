package com.jobee.admin.service.application.usecases.category.retrieve;

import com.jobee.admin.service.application.usecases.UseCase;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.commons.pagination.Search;
import com.jobee.admin.service.domain.commons.pagination.Pagination;

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
