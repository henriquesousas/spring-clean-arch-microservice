package com.jobee.admin.service.infrastructure.review;

import com.jobee.admin.service.application.usecases.review.ReviewOutputDto;
import com.jobee.admin.service.infrastructure.genre.models.GenreResponse;
import com.jobee.admin.service.infrastructure.review.dtos.CreateReviewRequestDto;
import com.jobee.admin.service.infrastructure.review.models.ReviewResponse;
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

@RequestMapping(value = "reviews")
@Tag(name = "Review")
public interface ReviewRestController {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review created"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> create(@RequestBody @Valid CreateReviewRequestDto request);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a review by identifier")
    ResponseEntity<ReviewResponse> getById(@PathVariable("id") String id);

    @GetMapping
    @Operation(summary = "Get a review by status/userid or both")
    ResponseEntity<List<ReviewResponse>> get(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String userId
    );
}
