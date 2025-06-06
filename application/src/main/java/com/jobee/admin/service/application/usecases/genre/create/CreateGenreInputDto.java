package com.jobee.admin.service.application.usecases.genre.create;

import java.util.List;

public record CreateGenreInputDto(
        String name,
        String description,
        List<String> categories
) {

    public static CreateGenreInputDto with(
            final String name,
            final String description,
            final List<String> categories
    ) {
        return new CreateGenreInputDto(name, description, categories);
    }

}
