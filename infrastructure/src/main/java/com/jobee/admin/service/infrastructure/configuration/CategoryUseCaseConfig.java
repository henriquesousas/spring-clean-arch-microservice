package com.jobee.admin.service.infrastructure.configuration;

import com.jobee.admin.service.application.category.cretate.CreateCategoryUseCase;
import com.jobee.admin.service.application.category.delete.DeleteCategoryUseCase;
import com.jobee.admin.service.application.category.retrieve.GetCategoryByIdUseCase;
import com.jobee.admin.service.application.category.retrieve.ListCategoryUseCase;
import com.jobee.admin.service.application.category.update.UpdateCategoryUseCase;
import com.jobee.admin.service.domain.category.CategoryRepositoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO: Renomear exe: CategoryUseCaseProvider
@Configuration
public class CategoryUseCaseConfig {

    private final CategoryRepositoryGateway categoryRepository;

    public CategoryUseCaseConfig(final CategoryRepositoryGateway categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new CreateCategoryUseCase(categoryRepository);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new GetCategoryByIdUseCase(categoryRepository);
    }

    @Bean
    public ListCategoryUseCase listCategoryUseCase() {
        return new ListCategoryUseCase(categoryRepository);
    }


    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new UpdateCategoryUseCase(categoryRepository);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DeleteCategoryUseCase(categoryRepository);
    }
}
