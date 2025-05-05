package com.jobee.admin.service.application.category.delete;

import com.jobee.admin.service.application.Unit;
import com.jobee.admin.service.application.UseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepositoryGateway;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.validation.handler.Notification;
import io.vavr.control.Either;
import io.vavr.control.Try;
import static io.vavr.control.Either.left;

// TODO: Receber uma string que será o Category Id e aqui validar
public class DeleteCategoryUseCase extends UseCase<CategoryId, Either<Notification, Unit>> {

    private final CategoryRepositoryGateway repository;

    public DeleteCategoryUseCase(final CategoryRepositoryGateway repository) {
        this.repository = repository;
    }

    @Override
    public Either<Notification, Unit> execute(CategoryId id) {
        Category category = this.repository.findById(id).orElse(null);

        if (category == null) {
            return left(Notification.create().append(NotFoundException.with(Category.class, id)));
        }

        return Try.run(() -> this.repository.delete(id))
                .toEither()
                .map(v -> new Unit())
                .mapLeft(Notification::create);
    }
}
