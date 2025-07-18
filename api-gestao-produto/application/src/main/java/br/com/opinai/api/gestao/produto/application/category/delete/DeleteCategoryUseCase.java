package br.com.opinai.api.gestao.produto.application.category.delete;

import br.com.opinai.api.gestao.produto.domain.category.Category;
import br.com.opinai.api.gestao.produto.domain.category.CategoryId;
import br.com.opinai.api.gestao.produto.domain.category.CategoryRepository;
import com.opinai.shared.application.Unit;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.NotFoundException;
import com.opinai.shared.domain.validation.handler.Notification;
import io.vavr.control.Either;
import io.vavr.control.Try;

public class DeleteCategoryUseCase extends UseCase<String, Either<Notification, Unit>> {

    private final CategoryRepository repository;

    public DeleteCategoryUseCase(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<Notification, Unit> execute(String id) {

        CategoryId categoryId = CategoryId.from(id);

        if(categoryId.getNotification().hasError()) {
            return Either.left(categoryId.getNotification());
        }

        Category category = this.repository.findById(categoryId).orElse(null);

        if (category == null) {
            return Either.left(Notification.create().append(NotFoundException.with(Category.class, categoryId)));
        }

        return Try.run(() -> this.repository.delete(categoryId))
                .toEither()
                .map(v -> new Unit())
                .mapLeft(Notification::create);
    }
}
