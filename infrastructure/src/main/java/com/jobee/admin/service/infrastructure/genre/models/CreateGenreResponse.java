package com.jobee.admin.service.infrastructure.genre.models;

public record CreateGenreResponse(
        String id
) {

    public static CreateGenreResponse with(
            final String id
    ) {
        return new CreateGenreResponse(id);
    }
}
