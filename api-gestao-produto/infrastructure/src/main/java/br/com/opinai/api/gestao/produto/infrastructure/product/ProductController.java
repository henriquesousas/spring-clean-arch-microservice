package br.com.opinai.api.gestao.produto.infrastructure.product;


import br.com.opinai.api.gestao.produto.application.product.usecase.GetAllProductsUseCase;
import br.com.opinai.api.gestao.produto.application.product.usecase.GetProductByIdUseCase;
import br.com.opinai.api.gestao.produto.application.product.usecase.ProductOutput;
import br.com.opinai.api.gestao.produto.domain.product.ProductSearch;
import br.com.opinai.api.gestao.produto.domain.product.ProductId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController implements ProductApi {

    @Autowired
    private GetProductByIdUseCase getProductByIdUseCase;

    @Autowired
    private GetAllProductsUseCase getAllProductsUseCase;

    @Override
    public ResponseEntity<ProductOutput> getById(final String id) {
        final var productId = ProductId.from(id);
        return this.getProductByIdUseCase.execute(productId)
                .map(ResponseEntity::ok)
                .getOrElseThrow(error -> error);
    }

    @Override
    public ResponseEntity<?> getAll(
            final String search,
            final String category,
            final int page,
            final int perPage,
            final String sort,
            final String dir
    ) {
        ;
        final var command = new ProductSearch(
                category,
                page,
                perPage,
                search,
                sort,
                dir
        );

        return this.getAllProductsUseCase.execute(command)
                .map(response -> ResponseEntity.ok(PaginationProductResponse.from(response)))
                .getOrElseThrow(er -> er);

    }
}
