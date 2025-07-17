package com.opinai.product.application.category.create;

import com.opinai.product.domain.category.CategoryBuilder;
import com.opinai.product.domain.category.CategoryRepository;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.ValidationException;
import com.opinai.shared.domain.validation.handler.Notification;
import io.vavr.control.Either;


public class CreateCategoryUseCase extends UseCase<CreateCategoryInputDto, Either<DomainException, CreateCategoryOutputDto>> {

    private final CategoryRepository repository;

    public CreateCategoryUseCase(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<DomainException, CreateCategoryOutputDto> execute(final CreateCategoryInputDto dto) {

        final var category = CategoryBuilder.newCategory(dto.name()).build();

        Notification notification = Notification.create();
        category.validate(notification);

        return notification.hasError()
                ? Either.left(ValidationException.with(notification.getErrors()))
                : Either.right(CreateCategoryOutputDto.from(this.repository.create(category)));
    }
}
