package br.com.opinai.api.gestao.produto.application.product.usecase;

import br.com.opinai.api.gestao.produto.domain.product.ProductId;
import br.com.opinai.api.gestao.produto.domain.product.ProductRepository;
import br.com.opinai.api.gestao.produto.domain.product.exception.ProductNotFoundException;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import io.vavr.control.Either;

public class GetProductByIdUseCase extends UseCase<ProductId, Either<DomainException, ProductOutput>> {

    private final ProductRepository repository;

    public GetProductByIdUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<DomainException, ProductOutput> execute(final ProductId productId) {
       return this.repository.getById(productId)
                .map(ProductOutput::from)
                .map(Either::<DomainException, ProductOutput>right)
                .orElseGet(() -> Either.left(new ProductNotFoundException()));
    }
}
