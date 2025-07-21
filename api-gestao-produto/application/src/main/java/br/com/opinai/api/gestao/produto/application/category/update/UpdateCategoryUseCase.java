package br.com.opinai.api.gestao.produto.application.category.update;

import br.com.opinai.api.gestao.produto.domain.category.Category;
import br.com.opinai.api.gestao.produto.domain.category.CategoryId;
import br.com.opinai.api.gestao.produto.domain.category.CategoryRepository;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.ApplicationException;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.NotFoundException;
import com.opinai.shared.domain.exceptions.ValidationException;
import com.opinai.shared.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;


public class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<DomainException, UpdateCategoryOutputDto>> {

    private final CategoryRepository repository;

    public UpdateCategoryUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<DomainException, UpdateCategoryOutputDto> execute(UpdateCategoryCommand dto) {

        CategoryId categoryId = CategoryId.from(dto.id());
        Notification notification = Notification.create();

        Category category = this.repository.findById(categoryId)
                .orElse(null);

        if (category == null) {
            return Either.left(NotFoundException.with(Category.class, categoryId));
        }

        category.update(dto.name()).validate(notification);

        if (notification.hasError()) {
            return Either.left(ValidationException.with(notification.getErrors()));
        }

        return API.Try(() -> this.repository.update(category))
                .toEither()
                .map(UpdateCategoryOutputDto::from)
                .mapLeft(ApplicationException::with);
    }
}
