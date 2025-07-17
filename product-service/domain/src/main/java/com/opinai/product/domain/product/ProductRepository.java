package com.opinai.product.domain.product;

import com.opinai.shared.domain.pagination.Pagination;
import com.opinai.shared.domain.pagination.Search;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> getById(ProductId identifier);
    Optional<Product> getByCategoryId(String identifier);
    Pagination<Product> getAll(ProductSearch query);
}
