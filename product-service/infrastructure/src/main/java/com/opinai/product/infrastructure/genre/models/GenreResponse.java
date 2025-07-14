package com.opinai.product.infrastructure.genre.models;


import com.opinai.product.domain.genre.Genre;

import java.time.Instant;

public record GenreResponse(
        String id,
        String name,
        String description,
        Instant createdAt
) {

    public static GenreResponse with(Genre genre    ) {
        return new GenreResponse(genre.getId().getValue(), genre.getName(), genre.getDescription(), genre.getCreatedAt());
    }
}
