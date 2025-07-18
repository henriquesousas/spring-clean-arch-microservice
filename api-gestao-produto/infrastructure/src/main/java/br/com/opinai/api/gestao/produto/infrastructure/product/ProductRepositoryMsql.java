package br.com.opinai.api.gestao.produto.infrastructure.product;

import br.com.opinai.api.gestao.produto.domain.product.Product;
import br.com.opinai.api.gestao.produto.domain.product.ProductId;
import br.com.opinai.api.gestao.produto.domain.product.ProductRepository;
import br.com.opinai.api.gestao.produto.domain.product.ProductSearch;
import br.com.opinai.api.gestao.produto.infrastructure.core.SpecificationUtils;
import br.com.opinai.api.gestao.produto.infrastructure.category.models.CategoryJpaEntity;
import com.opinai.shared.domain.pagination.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
                Join<ProductJpaEntity, CategoryJpaEntity> categoryJoin = root.join("category");
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
