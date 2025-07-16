package com.opinai.product.domain.product;

import com.opinai.product.domain.category.CategoryId;
import com.opinai.shared.domain.AggregateRoot;
import com.opinai.shared.domain.validation.ValidationHandler;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
public class Product extends AggregateRoot<ProductId> {

    private String name;
    private String description;
    private Brand brand;
    private Model model;
    private CategoryId categoryId;
    private String color;
    private Set<Photo> photos;
    private Set<Tags> tags;
    private OficialSite oficialSite;
    private Map<String, Object> specs;

    public Product(final ProductId productId) {
        super(productId);
    }

    @Override
    public void validate(ValidationHandler handler) {

    }
}
