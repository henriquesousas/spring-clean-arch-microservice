package com.jobee.admin.service.application.genre;

public record CreateGenreInputDto(
        String name,
        String description,
        String[] categories
) {
}
