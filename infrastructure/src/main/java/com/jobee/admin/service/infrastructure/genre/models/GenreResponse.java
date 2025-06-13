package com.jobee.admin.service.infrastructure.genre.models;

import com.jobee.admin.service.domain.genre.Genre;

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
