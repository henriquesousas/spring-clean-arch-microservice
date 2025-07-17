package com.opinai.product.infrastructure.subcategory;

import com.opinai.product.application.subcategory.create.CreateSubcategoryUseCase;
import com.opinai.product.application.subcategory.retrieve.GetSubcategoryByIdUseCase;
import com.opinai.product.domain.category.CategoryRepository;
import com.opinai.product.domain.subcategory.SubcategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubcategoryProviders {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubcategoryProviders(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Bean
    public CreateSubcategoryUseCase createGenreUseCase() {
        return new CreateSubcategoryUseCase(subcategoryRepository, categoryRepository);
    }

    @Bean
    public GetSubcategoryByIdUseCase getGenreByIdUseCase() {
        return new GetSubcategoryByIdUseCase(subcategoryRepository);
    }
}
