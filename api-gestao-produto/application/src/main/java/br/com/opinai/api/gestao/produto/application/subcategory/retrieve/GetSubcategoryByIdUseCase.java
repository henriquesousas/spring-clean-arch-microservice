package br.com.opinai.api.gestao.produto.application.subcategory.retrieve;

import br.com.opinai.api.gestao.produto.domain.subcategory.Subcategory;
import br.com.opinai.api.gestao.produto.domain.subcategory.SubcategoryId;
import br.com.opinai.api.gestao.produto.domain.subcategory.SubcategoryRepository;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.NotFoundException;
import com.opinai.shared.domain.exceptions.ValidationException;
import io.vavr.control.Either;

public class GetSubcategoryByIdUseCase extends UseCase<GetSubcategoryCommand, Either<DomainException, Subcategory>> {

    private final SubcategoryRepository repository;

    public GetSubcategoryByIdUseCase(final SubcategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<DomainException, Subcategory> execute(final GetSubcategoryCommand dto) {
        final var genreId = SubcategoryId.from(dto.subcategoryId());

        if (genreId.getNotification().hasError()) {
            final var errors = genreId.getNotification().getErrors();
            return Either.left(ValidationException.with(errors));
        }

        final var genre = this.repository.findById(genreId);

        return genre
                .<Either<DomainException, Subcategory>>map(Either::right)
                .orElseGet(() -> Either.left(NotFoundException.with(Subcategory.class, genreId)));
    }
}

