package com.opinai.product.infrastructure.category.repository;


import com.opinai.product.domain.category.Category;
import com.opinai.product.domain.category.CategoryBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

//TODOÇ Rename from CategoryModel to CategoryJpaEntity
@Data
@NoArgsConstructor
@Entity(name = "category")
@Table(name = "categories")
public class CategoryModel {

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

    private CategoryModel(
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }


    public static CategoryModel from(Category category) {
        return new CategoryModel(
                category.getId().getValue(),
                category.getName(),
                category.isActive(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                category.getDeletedAt()
        );
    }

    public Category toAggregate() {
        return new CategoryBuilder(this.getName())
                .withCategoryId(this.getId())
                .withActive(this.isActive())
                .withCreatedAt(this.getCreatedAt())
                .withUpdatedAt(this.getUpdatedAt())
                .withDeletedAt(this.getDeletedAt())
                .build();
    }
}
