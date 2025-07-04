package com.jobee.admin.service.infrastructure.category.repository;

import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.pagination.Pagination;
import com.jobee.admin.service.domain.pagination.Search;
import com.jobee.admin.service.infrastructure.utils.SpecificationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;


@Service
public class CategoryRepositoryMysql implements CategoryRepository {

    private final CategoryJpaRepository repository;

    public CategoryRepositoryMysql(final CategoryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(Category category) {
        CategoryModel categoryModel = this.repository.save(CategoryModel.from(category));
        return categoryModel.toAggregate();
    }

    @Override
    public Category update(Category category) {
        CategoryModel categoryModel = this.repository.save(CategoryModel.from(category));
        return categoryModel.toAggregate();
    }

    @Override
    public void delete(CategoryId identifier) {
        if (this.repository.existsById(identifier.getValue())) {
            this.repository.deleteById(identifier.getValue());
        }
    }

    @Override
    public Optional<Category> findById(CategoryId identifier) {
        return this.repository.findById(identifier.getValue())
                .map(CategoryModel::toAggregate);
    }

    @Override
    public Pagination<Category> findAll(Search query) {
        // Paginação
        final var page = PageRequest.of(
                query.page(),
                query.perPage(),
                Sort.by(Sort.Direction.fromString(query.directions()), query.sort())
        );

        // Busca dinamica pelo criterio terms (name ou description)
        final var specifications = Optional.ofNullable(query.terms())
                .filter(str -> !str.isBlank())
                .map(str -> {
                    final var nameLike = SpecificationUtils.<CategoryModel>like("name", str);
                    final var descriptionLike = SpecificationUtils.<CategoryModel>like("description", str);
                    return nameLike.or(descriptionLike);
                }).orElse(null);


        // Paginacao
        final var pageResult = this.repository.findAll (specifications, page);
        return new Pagination<>(
                page.getPageNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(CategoryModel::toAggregate).toList()

        );
    }

    @Override
    public List<CategoryId> existByIds(Iterable<CategoryId> categoryIDs) {
        final var ids = StreamSupport.stream(categoryIDs.spliterator(), false)
                .map(CategoryId::getValue)
                .toList();
        return this.repository.existsByIds(ids).stream()
                .map(CategoryId::from)
                .toList();
    }
}
