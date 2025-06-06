package com.jobee.admin.service.application.usecases.category.update;

import com.jobee.admin.service.application.usecases.UseCase;
import com.jobee.admin.service.domain.core.category.Category;
import com.jobee.admin.service.domain.core.category.CategoryId;
import com.jobee.admin.service.domain.core.category.CategoryRepository;
import com.jobee.admin.service.domain.exceptions.ApplicationException;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.exceptions.ValidationException;
import com.jobee.admin.service.domain.validation.handler.Notification;
import io.vavr.control.Either;


import static io.vavr.API.Try;
import static io.vavr.control.Either.left;


public class UpdateCategoryUseCase extends UseCase<UpdateCategoryInputDto, Either<DomainException, UpdateCategoryOutputDto>> {

    private final CategoryRepository repository;

    public UpdateCategoryUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<DomainException, UpdateCategoryOutputDto> execute(UpdateCategoryInputDto dto) {

        CategoryId categoryId = CategoryId.from(dto.id());
        Notification notification = Notification.create();

        Category category = this.repository.findById(categoryId)
                .orElse(null);

        if (category == null) {
            return left(NotFoundException.with(Category.class, categoryId));
        }

        category.update(dto.name(), dto.description()).validate(notification);

        if (notification.hasError()) {
            return left(ValidationException.with(notification.getErrors()));
        }

        return Try(() -> this.repository.update(category))
                .toEither()
                .map(UpdateCategoryOutputDto::from)
                .mapLeft(ApplicationException::with);
    }
}
