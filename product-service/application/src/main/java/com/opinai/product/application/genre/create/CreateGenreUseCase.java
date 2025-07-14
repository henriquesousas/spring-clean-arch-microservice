package com.opinai.product.application.genre.create;

import com.opinai.product.domain.category.CategoryId;
import com.opinai.product.domain.category.CategoryRepository;
import com.opinai.product.domain.genre.Genre;
import com.opinai.product.domain.genre.GenreBuilder;
import com.opinai.product.domain.genre.GenreRepository;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.ValidationException;
import com.opinai.shared.domain.validation.Error;
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
