package com.opinai.product.infrastructure.subcategory.repository;

import com.opinai.product.domain.subcategory.Subcategory;
import com.opinai.product.domain.subcategory.SubcategoryId;
import com.opinai.product.domain.subcategory.SubcategoryRepository;
import com.opinai.product.infrastructure.SpecificationUtils;
import com.opinai.shared.domain.pagination.Pagination;
import com.opinai.shared.domain.pagination.Search;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class SubcategoryRepositoryMysql implements SubcategoryRepository {

    private final SubcategoryJpaRepository repository;

    public SubcategoryRepositoryMysql(final SubcategoryJpaRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Subcategory create(final Subcategory subcategory) {
        final var genreJpaEntity = this.repository.save(SubCategoryJpaEntity.from(subcategory));
        return genreJpaEntity.toAggregate();
    }

    @Override
    public Subcategory update(Subcategory subcategory) {
        final var genreJpaEntity = this.repository.save(SubCategoryJpaEntity.from(subcategory));
        return genreJpaEntity.toAggregate();
    }

    @Override
    public Optional<Subcategory> findById(final SubcategoryId subcategoryId) {
        return this.repository.findById(subcategoryId.getValue())
                .map(SubCategoryJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Subcategory> findAll(Search query) {
        final var page = PageRequest.of(
                query.page(),
                query.perPage(),
                Sort.by(Sort.Direction.fromString(query.directions()), query.sort())
        );

        final var specifications = Optional.ofNullable(query.terms())
                .filter(str -> !str.isBlank())
                .map(terms -> SpecificationUtils.<SubCategoryJpaEntity>like("name", terms))
                .orElse(null);

        final var pageResult = this.repository.findAll(where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(SubCategoryJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public void delete(final SubcategoryId subcategoryId) {
        final var id = subcategoryId.getValue();
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        }
    }

    @Override
    public List<SubcategoryId> existsByIds(final Iterable<SubcategoryId> genreIDS) {
        final var ids = StreamSupport.stream(genreIDS.spliterator(), false)
                .map(SubcategoryId::getValue)
                .toList();

        return this.repository.existsByIds(ids).stream()
                .map(SubcategoryId::from)
                .toList();
    }
}
