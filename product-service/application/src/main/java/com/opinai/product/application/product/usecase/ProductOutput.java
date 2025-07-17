package com.opinai.product.application.product.usecase;

import com.opinai.product.domain.product.BrandRef;
import com.opinai.product.domain.product.Product;

public record ProductOutput(
        String productId,
        String name,
        String description,
        String model,
        BrandRef brand,
        String category,
        String subcategory,
        String color,
        String site
) {

    public static ProductOutput from(Product product) {
        return new ProductOutput(
                product.getAggregateId().getValue(),
                product.getName(),
                product.getDescription(),
                product.getModel().value(),
                product.getBrandRef(),
                product.getCategoryRef().name(),
                product.getSubcategoryRef().name(),
                product.getColor(),
                product.getSite().value());
    }
}
