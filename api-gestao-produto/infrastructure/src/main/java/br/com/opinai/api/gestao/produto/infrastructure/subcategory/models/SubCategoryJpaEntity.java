package br.com.opinai.api.gestao.produto.infrastructure.subcategory.models;


import br.com.opinai.api.gestao.produto.domain.category.CategoryId;
import br.com.opinai.api.gestao.produto.domain.subcategory.Subcategory;
import br.com.opinai.api.gestao.produto.domain.subcategory.SubcategoryBuilder;
import br.com.opinai.api.gestao.produto.domain.subcategory.SubcategoryId;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "Subcategories")
@Table(name = "subcategories")
public class SubCategoryJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    //user property from GenreCategoryJpaEntity (subcategoryId)
    @OneToMany(mappedBy = "subcategoryId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<SubcategoryCategoryJapEntity> categories;

    public SubCategoryJpaEntity(
            final String id,
            final String name,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.categories = new HashSet<>();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static SubCategoryJpaEntity from(final Subcategory subcategory) {
        final var entity = new SubCategoryJpaEntity(
                subcategory.getId().getValue(),
                subcategory.getName(),
                subcategory.isActive(),
                subcategory.getCreatedAt(),
                subcategory.getUpdatedAt(),
                subcategory.getDeletedAt()
        );

        subcategory.getCategories().forEach(entity::addCategory);

        return entity;
    }

    public Subcategory toAggregate() {
        return new SubcategoryBuilder(
                getName(),
                getCategories()
                        .stream()
                        .map(it -> CategoryId.from(it.getId().getCategoryId())).toList())
                .withId(SubcategoryId.from(getId()))
                .withActive(isActive())
                .withCreatedAt(getCreatedAt())
                .withUpdatedAt(getUpdatedAt())
                .withDeletedAt(getDeletedAt())
                .build();
    }

    public void addCategory(final CategoryId categoryId) {
        this.categories.add(SubcategoryCategoryJapEntity.from(this, categoryId));
    }

    public void removeCategory(final CategoryId categoryId) {
        this.categories.remove(SubcategoryCategoryJapEntity.from(this, categoryId));
    }
}
