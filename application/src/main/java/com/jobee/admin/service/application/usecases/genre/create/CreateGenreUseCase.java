package com.jobee.admin.service.application.usecases.genre.create;

import com.jobee.admin.service.application.usecases.UseCase;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.genre.Genre;
import com.jobee.admin.service.domain.genre.GenreBuilder;
import com.jobee.admin.service.domain.genre.GenreRepository;
import com.jobee.admin.service.domain.commons.exceptions.DomainException;
import com.jobee.admin.service.domain.commons.exceptions.ValidationException;
import com.jobee.admin.service.domain.commons.validation.Error;
import io.vavr.control.Either;

import java.util.List;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;


public class CreateGenreUseCase extends UseCase<CreateGenreInputDto, Either<DomainException, Genre>> {
    private final GenreRepository genreRepository;
    private final CategoryRepository categoryRepository;

    public CreateGenreUseCase(final GenreRepository repository,
                              final CategoryRepository categoryRepository) {
        this.genreRepository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Either<DomainException, Genre> execute(final CreateGenreInputDto dto) {

        final var ids = CategoryId.from(dto.categories());

        if (ids.isEmpty()) {
            return left(ValidationException.with(new Error("CategoryId cannot be null or empty")));
        }

        List<CategoryId> categoryIds = this.categoryRepository.existByIds(ids);

        final var genre = new GenreBuilder(dto.name(), dto.description(), categoryIds).build();

        if (genre.getNotification().hasError()) {
            return left(ValidationException.with(genre.getNotification().getErrors()));
        }

        return right(this.genreRepository.create(genre));
    }
}
