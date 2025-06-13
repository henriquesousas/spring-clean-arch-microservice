package com.jobee.admin.service.infrastructure.genre;


import com.jobee.admin.service.infrastructure.genre.models.CreateGenreResponse;
import com.jobee.admin.service.infrastructure.genre.models.GenreResponse;
import com.jobee.admin.service.infrastructure.genre.models.CreateGenreRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "genres")
@Tag(name = "Genres")
public interface HttpGenreController {


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
    ResponseEntity<CreateGenreResponse> create(@RequestBody @Valid CreateGenreRequest request);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a genre by identifier")
    ResponseEntity<GenreResponse> getById(@PathVariable("id") String id);

}
