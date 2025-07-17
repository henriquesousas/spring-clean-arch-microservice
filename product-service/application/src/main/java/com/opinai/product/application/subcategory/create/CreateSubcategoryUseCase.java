package com.opinai.product.application.subcategory.create;

import com.opinai.product.domain.category.CategoryId;
import com.opinai.product.domain.category.CategoryRepository;
import com.opinai.product.domain.subcategory.Subcategory;
import com.opinai.product.domain.subcategory.SubcategoryBuilder;
import com.opinai.product.domain.subcategory.SubcategoryRepository;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.ValidationException;
import com.opinai.shared.domain.validation.Error;
import io.vavr.control.Either;

import java.util.List;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;


public class CreateSubcategoryUseCase extends UseCase<CreateSubcategoryInputDto, Either<DomainException, Subcategory>> {
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    public CreateSubcategoryUseCase(final SubcategoryRepository repository,
                                    final CategoryRepository categoryRepository) {
        this.subcategoryRepository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Either<DomainException, Subcategory> execute(final CreateSubcategoryInputDto dto) {

        final var ids = CategoryId.from(dto.categories());

        if (ids.isEmpty()) {
            return left(ValidationException.with(new Error("CategoryId cannot be null or empty")));
        }

        List<CategoryId> categoryIds = this.categoryRepository.existByIds(ids);

        final var genre = new SubcategoryBuilder(dto.name(),categoryIds).build();

        if (genre.getNotification().hasError()) {
            return left(ValidationException.with(genre.getNotification().getErrors()));
        }

        return right(this.subcategoryRepository.create(genre));
    }
}
