package com.jobee.admin.service.application.category.retrieve;

import com.jobee.admin.service.application.UseCase;
import com.jobee.admin.service.domain.category.CategoryRepositoryGateway;
import com.jobee.admin.service.domain.category.CategorySearch;
import com.jobee.admin.service.domain.pagination.Pagination;

public class ListCategoryUseCase extends UseCase<CategorySearch, Pagination<GetCategoryOutput>> {

    private final CategoryRepositoryGateway repository;

    public ListCategoryUseCase(final CategoryRepositoryGateway repository) {
        this.repository = repository;
    }

    @Override
    public Pagination<GetCategoryOutput> execute(CategorySearch categorySearch) {
        return this.repository
                .findAll(categorySearch)
                .map(GetCategoryOutput::from);

    }
}
