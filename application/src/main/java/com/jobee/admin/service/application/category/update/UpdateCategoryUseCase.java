package com.jobee.admin.service.application.category.update;

import com.jobee.admin.service.application.UseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepositoryGateway;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.validation.handler.Notification;
import io.vavr.control.Either;


import static io.vavr.API.Try;
import static io.vavr.control.Either.left;


public class UpdateCategoryUseCase extends UseCase<UpdateCategoryInputDto, Either<Notification, UpdateCategoryOutputDto>> {

    private CategoryRepositoryGateway repository;

    public UpdateCategoryUseCase(CategoryRepositoryGateway repository) {
        this.repository = repository;
    }

    @Override
    public Either<Notification, UpdateCategoryOutputDto> execute(UpdateCategoryInputDto dto) {

        CategoryId categoryId = CategoryId.from(dto.id());
        Notification notification = Notification.create();

        Category category = this.repository.findById(categoryId)
                .orElse(null);

        if (category == null) {
            return left(notification.append(NotFoundException.with(Category.class, categoryId)));
        }

        category.update(dto.name(), dto.description())
                .validate(notification);

        if (notification.hasError()) {
            return left(notification);
        }

        return Try(() -> this.repository.update(category))
                .toEither()
                .map(UpdateCategoryOutputDto::from)
                .mapLeft(Notification::create);
    }
}
