package com.opinai.product.infrastructure.genre.models;

public record CreateGenreResponse(
        String id
) {

    public static CreateGenreResponse with(
            final String id
    ) {
        return new CreateGenreResponse(id);
    }
}
