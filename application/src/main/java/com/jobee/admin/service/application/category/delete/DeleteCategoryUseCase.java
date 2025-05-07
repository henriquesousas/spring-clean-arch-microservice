package com.jobee.admin.service.application.category.delete;

import com.jobee.admin.service.application.Unit;
import com.jobee.admin.service.application.UseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.validation.handler.Notification;
import io.vavr.control.Either;
import io.vavr.control.Try;
import static io.vavr.control.Either.left;

public class DeleteCategoryUseCase extends UseCase<String, Either<Notification, Unit>> {

    private final CategoryRepository repository;

    public DeleteCategoryUseCase(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<Notification, Unit> execute(String id) {

        CategoryId categoryId = CategoryId.from(id);

        if(categoryId.getNotification().hasError()) {
            return left(categoryId.getNotification());
        }

        Category category = this.repository.findById(categoryId).orElse(null);

        if (category == null) {
            return left(Notification.create().append(NotFoundException.with(Category.class, categoryId)));
        }

        return Try.run(() -> this.repository.delete(categoryId))
                .toEither()
                .map(v -> new Unit())
                .mapLeft(Notification::create);
    }
}
