package br.com.opinai.api.gestao.produto.domain.product;

import com.opinai.shared.domain.pagination.Pagination;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> getById(ProductId identifier);
    Pagination<Product> getAll(ProductSearch query);
}
