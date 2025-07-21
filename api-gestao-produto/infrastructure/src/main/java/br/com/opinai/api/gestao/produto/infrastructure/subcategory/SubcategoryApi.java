<<<<<<<< HEAD:product-service/infrastructure/src/main/java/com/opinai/product/infrastructure/genre/HttpGenreController.java
package com.opinai.product.infrastructure.genre;


import com.opinai.product.infrastructure.genre.models.CreateGenreResponse;
import com.opinai.product.infrastructure.genre.models.GenreResponse;
import com.opinai.product.infrastructure.genre.models.CreateGenreRequest;
========
package br.com.opinai.api.gestao.produto.infrastructure.subcategory;


import br.com.opinai.api.gestao.produto.infrastructure.subcategory.models.CreateSubcategoryResponse;
import br.com.opinai.api.gestao.produto.infrastructure.subcategory.models.SubcategoryResponse;
import br.com.opinai.api.gestao.produto.infrastructure.subcategory.models.CreateSubcategoryRequest;
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/main/java/br/com/opinai/api/gestao/produto/infrastructure/subcategory/SubcategoryApi.java
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

<<<<<<<< HEAD:product-service/infrastructure/src/main/java/com/opinai/product/infrastructure/genre/HttpGenreController.java
@RequestMapping(value = "genres")
@Tag(name = "Genres")
public interface HttpGenreController {
========
@RequestMapping(value = "subcategories")
@Tag(name = "Subcategory")
public interface SubcategoryApi {
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/main/java/br/com/opinai/api/gestao/produto/infrastructure/subcategory/SubcategoryApi.java


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Genre created"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
<<<<<<<< HEAD:product-service/infrastructure/src/main/java/com/opinai/product/infrastructure/genre/HttpGenreController.java
    ResponseEntity<CreateGenreResponse> create(@RequestBody @Valid CreateGenreRequest request);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a genre by identifier")
    ResponseEntity<GenreResponse> getById(@PathVariable("id") String id);
========
    ResponseEntity<CreateSubcategoryResponse> create(@RequestBody @Valid CreateSubcategoryRequest request);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a genre by identifier")
    ResponseEntity<SubcategoryResponse> getById(@PathVariable("id") String id);
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/main/java/br/com/opinai/api/gestao/produto/infrastructure/subcategory/SubcategoryApi.java

}
