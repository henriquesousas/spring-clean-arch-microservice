package com.jobee.admin.service.infrastructure.genre;

import com.jobee.admin.service.application.genre.create.CreateGenreUseCase;
import com.jobee.admin.service.application.genre.retrieve.GetGenreByIdUseCase;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.genre.GenreRepository;
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
