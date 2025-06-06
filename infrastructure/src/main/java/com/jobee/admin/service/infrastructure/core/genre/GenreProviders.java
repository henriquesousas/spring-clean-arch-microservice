package com.jobee.admin.service.infrastructure.core.genre;

import com.jobee.admin.service.application.usecases.genre.create.CreateGenreUseCase;
import com.jobee.admin.service.application.usecases.genre.retrieve.GetGenreByIdUseCase;
import com.jobee.admin.service.domain.core.category.CategoryRepository;
import com.jobee.admin.service.domain.core.genre.GenreRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenreProviders {

    private final GenreRepository genreRepository;
    private final CategoryRepository categoryRepository;

    public GenreProviders(GenreRepository genreRepository, CategoryRepository categoryRepository) {
        this.genreRepository = genreRepository;
        this.categoryRepository = categoryRepository;
    }

    @Bean
    public CreateGenreUseCase createGenreUseCase() {
        return new CreateGenreUseCase(genreRepository, categoryRepository);
    }

    @Bean
    public GetGenreByIdUseCase getGenreByIdUseCase() {
        return new GetGenreByIdUseCase(genreRepository);
    }
}
