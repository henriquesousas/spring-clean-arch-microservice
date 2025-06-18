package com.jobee.admin.service.infrastructure.reviewanalysis;

import com.jobee.admin.service.infrastructure.reviewanalysis.dtos.CreateReviewAnalysisOutputDto;
import com.jobee.admin.service.infrastructure.reviewanalysis.dtos.CreateReviewAnalysisRequestDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@RequestMapping(value = "reviewanalysis")
@Tag(name = "ReviewAnalysis")
public interface HttpReviewAnalysisController {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review Analysis created"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<CreateReviewAnalysisOutputDto> create(@RequestBody @Valid CreateReviewAnalysisRequestDto request);
}
