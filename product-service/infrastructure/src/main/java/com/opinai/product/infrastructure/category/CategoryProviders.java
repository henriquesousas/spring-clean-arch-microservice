package com.opinai.product.infrastructure.category;

import com.opinai.product.application.category.create.CreateCategoryUseCase;
import com.opinai.product.application.category.delete.DeleteCategoryUseCase;
import com.opinai.product.application.category.retrieve.GetCategoryByIdUseCase;
import com.opinai.product.application.category.retrieve.ListCategoryUseCase;
import com.opinai.product.application.category.update.UpdateCategoryUseCase;
import com.opinai.product.domain.category.CategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class CategoryProviders {

    private final CategoryRepository repository;

    public CategoryProviders(final CategoryRepository repository) {
        this.repository = Objects.requireNonNull(repository);;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new CreateCategoryUseCase(repository);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new GetCategoryByIdUseCase(repository);
    }

    @Bean
    public ListCategoryUseCase listCategoryUseCase() {
        return new ListCategoryUseCase(repository);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new UpdateCategoryUseCase(repository);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DeleteCategoryUseCase(repository);
    }
}
