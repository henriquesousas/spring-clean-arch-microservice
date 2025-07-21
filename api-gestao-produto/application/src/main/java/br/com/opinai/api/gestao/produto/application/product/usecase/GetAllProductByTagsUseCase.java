package br.com.opinai.api.gestao.produto.application.product.usecase;

import br.com.opinai.api.gestao.produto.domain.product.ProductRepository;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import io.vavr.control.Either;

import java.util.List;

public class GetAllProductByTagsUseCase extends UseCase<List<String>, Either<DomainException, List<ProductOutput>>> {

    private final ProductRepository repository;

    public GetAllProductByTagsUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<DomainException, List<ProductOutput>> execute(final List<String> tagsId) {
        final var products = this.repository
                .getByTags(tagsId)
                .stream()
                .map(ProductOutput::from)
                .toList();

        return Either.right(products);
    }
}
