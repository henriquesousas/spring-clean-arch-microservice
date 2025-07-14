package com.opinai.product.infrastructure.genre;

import com.opinai.product.application.genre.create.CreateGenreInputDto;
import com.opinai.product.application.genre.create.CreateGenreUseCase;
import com.opinai.product.application.genre.retrieve.GetGenreByIdInputDto;
import com.opinai.product.application.genre.retrieve.GetGenreByIdUseCase;
import com.opinai.product.infrastructure.genre.models.CreateGenreResponse;
import com.opinai.product.infrastructure.genre.models.GenreResponse;
import com.opinai.product.infrastructure.genre.models.CreateGenreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class GenreController implements HttpGenreController {

    @Autowired
    private CreateGenreUseCase createGenreUseCase;

    @Autowired
    private GetGenreByIdUseCase getGenreByIdUseCase;

    @Override
    public ResponseEntity<CreateGenreResponse> create(CreateGenreRequest request) {

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

    @Override
    public ResponseEntity<GenreResponse> getById(String id) {
        final var command = new GetGenreByIdInputDto(id);
        return getGenreByIdUseCase.execute(command)
                .map(data -> ResponseEntity.ok(GenreResponse.with(data))
                )
                .getOrElseThrow(error -> error);
    }
}
