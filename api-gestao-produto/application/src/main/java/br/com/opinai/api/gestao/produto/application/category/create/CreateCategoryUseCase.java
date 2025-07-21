package br.com.opinai.api.gestao.produto.application.category.create;

import br.com.opinai.api.gestao.produto.domain.category.CategoryBuilder;
import br.com.opinai.api.gestao.produto.domain.category.CategoryRepository;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.ValidationException;
import com.opinai.shared.domain.validation.handler.Notification;
import io.vavr.control.Either;


public class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<DomainException, CreateCategoryOutput>> {

    private final CategoryRepository repository;

    public CreateCategoryUseCase(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<DomainException, CreateCategoryOutput> execute(final CreateCategoryCommand dto) {

        final var category = CategoryBuilder.newCategory(dto.name()).build();

        Notification notification = Notification.create();
        category.validate(notification);

        return notification.hasError()
                ? Either.left(ValidationException.with(notification.getErrors()))
                : Either.right(CreateCategoryOutput.from(this.repository.create(category)));
    }
}
