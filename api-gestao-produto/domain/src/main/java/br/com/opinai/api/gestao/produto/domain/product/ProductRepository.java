package br.com.opinai.api.gestao.produto.domain.product;

import com.opinai.shared.domain.pagination.Pagination;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> getById(ProductId identifier);
    List<Product> getByTags(List<String> tagsId);
    Pagination<Product> getAll(ProductSearch query);
}
