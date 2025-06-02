package com.jobee.admin.service.application.category.create;

import com.jobee.admin.service.application.UseCase;
import com.jobee.admin.service.domain.category.CategoryBuilder;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.exceptions.ValidationException;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.validation.handler.Notification;
import io.vavr.control.Either;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

public class CreateCategoryUseCase extends UseCase<
        CreateCategoryInputDto,
        Either<DomainException, CreateCategoryOutputDto>> {

    private final CategoryRepository repository;

    public CreateCategoryUseCase(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<DomainException, CreateCategoryOutputDto> execute(final CreateCategoryInputDto dto) {

        final var category = CategoryBuilder.newCategory(dto.name(), dto.description()).build();

        Notification notification = Notification.create();
        category.validate(notification);

        return notification.hasError()
                ? left(ValidationException.with(notification.getErrors()))
                : right(CreateCategoryOutputDto.from(this.repository.create(category)));
    }
}
