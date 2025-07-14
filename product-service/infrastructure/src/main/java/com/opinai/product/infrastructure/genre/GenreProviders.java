package com.opinai.product.infrastructure.genre;

import com.opinai.product.application.genre.create.CreateGenreUseCase;
import com.opinai.product.application.genre.retrieve.GetGenreByIdUseCase;
import com.opinai.product.domain.category.CategoryRepository;
import com.opinai.product.domain.genre.GenreRepository;
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
