package com.jobee.admin.service.infrastructure.genre;

import com.jobee.admin.service.application.genre.CreateGenreUseCase;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.genre.GenreRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenreProviders {

    private GenreRepository genreRepository;
    private CategoryRepository categoryRepository;

    @Bean
    public CreateGenreUseCase createGenreUseCase() {
        return new CreateGenreUseCase(genreRepository, categoryRepository);
    }
}
