package com.opinai.product.application.product.usecase;

import com.opinai.product.domain.product.ProductRepository;
import com.opinai.product.domain.product.ProductSearch;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.pagination.Pagination;
import io.vavr.control.Either;

import java.util.Objects;

public class GetAllProductsUseCase extends UseCase<ProductSearch, Either<DomainException, Pagination<ProductOutput>>> {

    private final ProductRepository repository;

    public GetAllProductsUseCase(ProductRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<DomainException, Pagination<ProductOutput>> execute(ProductSearch search) {
        final var data = this.repository
                .getAll(search)
                .map(ProductOutput::from);

        return Either.right(data);
    }
}
