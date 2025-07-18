package br.com.opinai.api.gestao.produto.infrastructure.category;

import br.com.opinai.api.gestao.produto.application.category.create.CreateCategoryUseCase;
import br.com.opinai.api.gestao.produto.application.category.delete.DeleteCategoryUseCase;
import br.com.opinai.api.gestao.produto.application.category.retrieve.GetCategoryByIdUseCase;
import br.com.opinai.api.gestao.produto.application.category.retrieve.ListCategoryUseCase;
import br.com.opinai.api.gestao.produto.application.category.update.UpdateCategoryUseCase;
import br.com.opinai.api.gestao.produto.domain.category.CategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class CategoryBeans {

    private final CategoryRepository repository;

    public CategoryBeans(final CategoryRepository repository) {
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
