package com.opinai.product.infrastructure.subcategory;


import com.opinai.product.infrastructure.subcategory.models.CreateSubcategoryResponse;
import com.opinai.product.infrastructure.subcategory.models.SubcategoryResponse;
import com.opinai.product.infrastructure.subcategory.models.CreateSubcategoryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "subcategories")
@Tag(name = "Subcategory")
public interface HttpSubcategoryController {


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
    ResponseEntity<CreateSubcategoryResponse> create(@RequestBody @Valid CreateSubcategoryRequest request);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a genre by identifier")
    ResponseEntity<SubcategoryResponse> getById(@PathVariable("id") String id);

}
