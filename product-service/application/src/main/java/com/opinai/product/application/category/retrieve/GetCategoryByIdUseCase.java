package com.opinai.product.application.category.retrieve;

import com.opinai.product.domain.category.Category;
import com.opinai.product.domain.category.CategoryId;
import com.opinai.product.domain.category.CategoryRepository;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.NotFoundException;
import io.vavr.control.Either;

public class GetCategoryByIdUseCase extends UseCase<String, Either<DomainException, CategoryOutput>> {

    private final CategoryRepository repository;

    public GetCategoryByIdUseCase(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public Either<DomainException, CategoryOutput> execute(String id) {

        CategoryId categoryId = CategoryId.from(id);

        return repository.findById(categoryId)
                .<Either<DomainException, CategoryOutput>>map(category -> Either.right(CategoryOutput.from(category)))
                .orElseGet(() -> Either.left(NotFoundException.with(Category.class, categoryId)));

    }
}
