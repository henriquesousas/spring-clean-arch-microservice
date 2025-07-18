package br.com.opinai.api.gestao.produto.infrastructure.product;

import br.com.opinai.api.gestao.produto.application.product.usecase.ProductOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "products")
@Tag(name = "Products")
public interface ProductApi {

    @GetMapping(value = "/{id}")
    @Operation(summary = "Pesquisa um produto por seu código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno do servidor"),
    })
    ResponseEntity<ProductOutput> getById(final @PathVariable String id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno do servidor"),
    })
    ResponseEntity<?> getAll(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "category", required = false, defaultValue = "") final String category,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "name") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String dir
    );
}
