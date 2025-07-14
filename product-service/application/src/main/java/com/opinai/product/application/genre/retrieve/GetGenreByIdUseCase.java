package com.opinai.product.application.genre.retrieve;

import com.opinai.product.domain.genre.Genre;
import com.opinai.product.domain.genre.GenreId;
import com.opinai.product.domain.genre.GenreRepository;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.NotFoundException;
import com.opinai.shared.domain.exceptions.ValidationException;
import io.vavr.control.Either;

public class GetGenreByIdUseCase extends UseCase<GetGenreByIdInputDto, Either<DomainException, Genre>> {

    private final GenreRepository repository;

    public GetGenreByIdUseCase(final GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<DomainException, Genre> execute(final GetGenreByIdInputDto dto) {
        final var genreId = GenreId.from(dto.genreId());

        if (genreId.getNotification().hasError()) {
            final var errors = genreId.getNotification().getErrors();
            return Either.left(ValidationException.with(errors));
        }

        final var genre = this.repository.findById(genreId);

        return genre
                .<Either<DomainException, Genre>>map(Either::right)
                .orElseGet(() -> Either.left(NotFoundException.with(Genre.class, genreId)));
    }
}

