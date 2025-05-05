package com.jobee.admin.service.application.category.retrieve;

import com.jobee.admin.service.application.UseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepositoryGateway;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

public class GetCategoryByIdUseCase extends UseCase<String, Either<Notification, GetCategoryOutput>> {

    private CategoryRepositoryGateway repository;

    public GetCategoryByIdUseCase(CategoryRepositoryGateway repository) {
        this.repository = repository;
    }


    @Override
    public Either<Notification, GetCategoryOutput> execute(String id) {

        CategoryId categoryId = CategoryId.from(id);

        final var orEleThrow = NotFoundException.with(
                Category.class,
                categoryId);

        return API.Try(() -> this.repository.findById(categoryId).orElseThrow(() -> orEleThrow))
                .toEither()
                .map((GetCategoryOutput::from))
                .mapLeft(Notification::create);
    }
}
