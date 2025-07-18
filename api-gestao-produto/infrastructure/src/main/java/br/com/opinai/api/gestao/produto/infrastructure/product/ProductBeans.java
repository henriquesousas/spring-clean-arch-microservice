package br.com.opinai.api.gestao.produto.infrastructure.product;

import br.com.opinai.api.gestao.produto.application.product.usecase.GetAllProductsUseCase;
import br.com.opinai.api.gestao.produto.application.product.usecase.GetProductByIdUseCase;
import br.com.opinai.api.gestao.produto.domain.product.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ProductBeans {

    private final ProductRepository repository;

    public ProductBeans(final ProductRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Bean
    public GetProductByIdUseCase getProductByIdUseCase() {
        return new GetProductByIdUseCase(repository);
    }

    @Bean
    public GetAllProductsUseCase getProductByCategoryUseCase() {
        return new GetAllProductsUseCase(repository);
    }
}
