package br.com.opinai.api.gestao.produto.infrastructure.subcategory;

import br.com.opinai.api.gestao.produto.application.subcategory.create.CreateSubcategoryUseCase;
import br.com.opinai.api.gestao.produto.application.subcategory.retrieve.GetSubcategoryByIdUseCase;
import br.com.opinai.api.gestao.produto.domain.category.CategoryRepository;
import br.com.opinai.api.gestao.produto.domain.subcategory.SubcategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubcategoryBeans {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubcategoryBeans(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository) {
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
