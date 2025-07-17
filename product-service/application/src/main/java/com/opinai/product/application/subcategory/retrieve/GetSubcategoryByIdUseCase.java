package com.opinai.product.application.subcategory.retrieve;

import com.opinai.product.domain.subcategory.Subcategory;
import com.opinai.product.domain.subcategory.SubcategoryId;
import com.opinai.product.domain.subcategory.SubcategoryRepository;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.NotFoundException;
import com.opinai.shared.domain.exceptions.ValidationException;
import io.vavr.control.Either;

public class GetSubcategoryByIdUseCase extends UseCase<GetSubcategoryByIdDto, Either<DomainException, Subcategory>> {

    private final SubcategoryRepository repository;

    public GetSubcategoryByIdUseCase(final SubcategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<DomainException, Subcategory> execute(final GetSubcategoryByIdDto dto) {
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

