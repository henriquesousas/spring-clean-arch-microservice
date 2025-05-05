package com.jobee.admin.service.application.category.cretate;

import com.jobee.admin.service.application.UseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryRepositoryGateway;
import com.jobee.admin.service.domain.validation.handler.Notification;
import io.vavr.control.Either;

import static io.vavr.API.Try;
import static io.vavr.control.Either.left;

public class CreateCategoryUseCase extends UseCase<CreateCategoryInputDto,
        Either<Notification, CreateCategoryOutputDto>> {

    private final CategoryRepositoryGateway repository;

    public CreateCategoryUseCase(final CategoryRepositoryGateway repository) {
        this.repository = repository;
    }

    @Override
    public Either<Notification, CreateCategoryOutputDto> execute(final CreateCategoryInputDto dto) {
        Category category = Category.newCategory(
                dto.name(),
                dto.description(),
                true
        );

        Notification notification = Notification.create();
        category.validate(notification);

        if (notification.hasError()) {
            return left(notification);
        }

        return Try(() -> this.repository.create(category))
                .toEither()
                .map(CreateCategoryOutputDto::from)
                .mapLeft(Notification::create);
    }
}
