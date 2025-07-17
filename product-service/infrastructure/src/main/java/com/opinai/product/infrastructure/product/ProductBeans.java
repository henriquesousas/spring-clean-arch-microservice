package com.opinai.product.infrastructure.product;

import com.opinai.product.application.product.usecase.GetAllProductsUseCase;
import com.opinai.product.application.product.usecase.GetProductByIdUseCase;
import com.opinai.product.domain.product.ProductRepository;
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
