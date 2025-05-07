package com.jobee.admin.service.application.category.retrieve;

import com.jobee.admin.service.application.UseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Optional;

import static io.vavr.API.Right;
import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

public class GetCategoryByIdUseCase extends UseCase<String, Either<DomainException, CategoryOutput>> {

    private final CategoryRepository repository;

    public GetCategoryByIdUseCase(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public Either<DomainException, CategoryOutput> execute(String id) {

        CategoryId categoryId = CategoryId.from(id);

        return repository.findById(categoryId)
                .<Either<DomainException, CategoryOutput>>map(category -> right(CategoryOutput.from(category)))
                .orElseGet(() -> left(NotFoundException.with(Category.class, categoryId)));

    }
}
