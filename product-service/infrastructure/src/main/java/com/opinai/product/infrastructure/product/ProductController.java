package com.opinai.product.infrastructure.product;


import com.opinai.product.application.product.usecase.GetAllProductsUseCase;
import com.opinai.product.application.product.usecase.GetProductByIdUseCase;
import com.opinai.product.application.product.usecase.ProductOutput;
import com.opinai.product.domain.product.ProductSearch;
import com.opinai.product.domain.product.ProductId;
import com.opinai.product.infrastructure.core.models.ObjectResponse;
import com.opinai.shared.domain.pagination.Search;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "products")
@Tag(name = "Products")
public class ProductController {

    @Autowired
    private GetProductByIdUseCase getProductByIdUseCase;

    @Autowired
    private GetAllProductsUseCase productListAllUseCase;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ObjectResponse<ProductOutput>> getById(final @PathVariable String id) {
        final var productId = ProductId.from(id);
        return this.getProductByIdUseCase.execute(productId)
                .map(productOutput -> ResponseEntity.ok(ObjectResponse.from(productOutput)))
                .getOrElseThrow(error -> error);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NotFound"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    public ResponseEntity<?> listAll(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "category", required = false, defaultValue = "") final String category,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "name") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String dir
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

        return this.productListAllUseCase.execute(command)
                .map(response -> ResponseEntity.ok(PaginationProductResponse.from(response)))
                .getOrElseThrow(er -> er);

    }
}
