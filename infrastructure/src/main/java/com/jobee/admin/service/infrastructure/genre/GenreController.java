package com.jobee.admin.service.infrastructure.genre;

import com.jobee.admin.service.application.genre.CreateGenreInputDto;
import com.jobee.admin.service.application.genre.CreateGenreUseCase;
import com.jobee.admin.service.infrastructure.genre.models.CreateGenreRequest;
import com.jobee.admin.service.infrastructure.genre.models.CreateGenreResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class GenreController implements HttpGenreController {

    private final CreateGenreUseCase createGenreUseCase;

    public GenreController(CreateGenreUseCase createGenreUseCase) {
        this.createGenreUseCase = createGenreUseCase;
    }

    @Override
    public ResponseEntity<?> create(CreateGenreRequest request) {

        final var command = CreateGenreInputDto.with(
                request.name(),
                request.description(),
                request.categories()
        );
        return this.createGenreUseCase.execute(command)
                .map(data -> {
                    final var response = CreateGenreResponse.with(data.getId().getValue());
                    final var location = URI.create("/genres/" + data.getId());
                    return ResponseEntity.created(location).body(response);
                })
                .getOrElseThrow(error -> error);
    }
}
