package br.com.opinai.api.gestao.produto.infrastructure.product;


import br.com.opinai.api.gestao.produto.application.product.usecase.GetAllProductByTagsUseCase;
import br.com.opinai.api.gestao.produto.application.product.usecase.GetAllProductsUseCase;
import br.com.opinai.api.gestao.produto.application.product.usecase.GetProductByIdUseCase;
import br.com.opinai.api.gestao.produto.application.product.usecase.ProductOutput;
import br.com.opinai.api.gestao.produto.domain.product.ProductSearch;
import br.com.opinai.api.gestao.produto.domain.product.ProductId;
import com.opinai.shared.domain.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController implements ProductApi {

    @Autowired
    private GetProductByIdUseCase getProductByIdUseCase;

    @Autowired
    private GetAllProductsUseCase getAllProductsUseCase;

    @Autowired
    private GetAllProductByTagsUseCase getAllProductByTagsUseCase;

    @Override
    public ResponseEntity<ProductOutput> getById(final String id) {
        final var productId = ProductId.from(id);
        return this.getProductByIdUseCase.execute(productId)
                .map(ResponseEntity::ok)
                .getOrElseThrow(error -> error);
    }

    @Override
    public ResponseEntity<List<ProductOutput>> getByTags(final String tagsIds) {

        final var tagIds = Arrays.stream(tagsIds.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        return this.getAllProductByTagsUseCase.execute(tagIds)

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
