package com.jobee.admin.service.infrastructure.review;

import com.jobee.admin.service.application.usecases.review.average.GetReviewAverageOutputCommand;
import com.jobee.admin.service.infrastructure.review.models.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Review")
public interface ReviewApi {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review created"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> create(@RequestBody @Valid CreateReviewRequestCommand request);

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review updated successfully"),
            @ApiResponse(responseCode = "404", description = "Review Not found"),
            @ApiResponse(responseCode = "422", description = "Unprocessable error"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    ResponseEntity<Void> update(
            @PathVariable(name = "id") String identifier,
            @RequestBody @Valid UpdateReviewRequestCommand command);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a review by identifier")
    ResponseEntity<ApiSingleResponse<ReviewOutputPreview>> getById(@PathVariable("id") String id);

    @GetMapping(value = "/average/{productId}")
    ResponseEntity<ApiSingleResponse<GetReviewAverageOutputCommand>> getReviewAverage(@PathVariable("productId") String productId);

    @GetMapping
    @Operation(summary = "Get a review by status/userid or both")
    ResponseEntity<ApiListResponse<List<ReviewOutputPreview>>> get(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String userId
    );

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Review Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    ResponseEntity<Void> delete(@PathVariable(name = "id") String identifier);
}
