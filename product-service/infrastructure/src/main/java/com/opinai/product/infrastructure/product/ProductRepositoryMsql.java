package com.opinai.product.infrastructure.product;

import com.opinai.product.domain.product.Product;
import com.opinai.product.domain.product.ProductId;
import com.opinai.product.domain.product.ProductRepository;
import com.opinai.product.domain.product.ProductSearch;
import com.opinai.product.infrastructure.category.repository.CategoryModel;
import com.opinai.product.infrastructure.core.SpecificationUtils;
import com.opinai.shared.domain.pagination.Pagination;
import com.opinai.shared.domain.pagination.Search;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import java.util.Objects;
import java.util.Optional;

@Component
public class ProductRepositoryMsql implements ProductRepository {

    private final ProductJpaRepository repository;

    public ProductRepositoryMsql(final ProductJpaRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Optional<Product> getById(final ProductId identifier) {
        return this.repository
                .findById(identifier.getValue())
                .map(ProductJpaEntity::toAggregate);
    }

    @Override
    public Optional<Product> getByCategoryId(String identifier) {
        return this.repository
                .findById(identifier)
                .map(ProductJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Product> getAll(ProductSearch query) {
        // Paginação
        final var page = PageRequest.of(
                query.page(),
                query.perPage(),
                Sort.by(Sort.Direction.fromString(query.directions()), query.sort())
        );

        Specification<ProductJpaEntity> specifications = Specification.where(null);

        // Filtra por termos
        if(StringUtils.isNotBlank(query.terms())) {
            Specification<ProductJpaEntity> nameLike = SpecificationUtils.like("name", query.terms());
            Specification<ProductJpaEntity> descriptionLike = SpecificationUtils.like("description", query.terms());
            specifications = specifications.and(nameLike.or(descriptionLike));
        }

        // Filtro por categoria (se fornecido)
        if (StringUtils.isNotBlank(query.categoryId())) {
            specifications = specifications.and((root, q, cb) -> {
                Join<ProductJpaEntity, CategoryModel> categoryJoin = root.join("category");
                return cb.equal(categoryJoin.get("id"), query.categoryId());
            });
        }

        final var pageResult = this.repository.findAll(specifications, page);

        return new Pagination<>(
                page.getPageNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(ProductJpaEntity::toAggregate).toList()

        );
    }
}
