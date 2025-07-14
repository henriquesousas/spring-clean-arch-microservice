package com.opinai.product.infrastructure.genre.repository;


import com.opinai.product.domain.category.CategoryId;
import com.opinai.product.domain.genre.Genre;
import com.opinai.product.domain.genre.GenreBuilder;
import com.opinai.product.domain.genre.GenreId;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Genre")
@Table(name = "genres")
public class GenreJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 4000)
    private String description;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    // Field of relationship
    @OneToMany(mappedBy = "genre", //use GenreCategoryJpaEntity
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private Set<GenreCategoryJpaEntity> categories;

    public GenreJpaEntity() {
    }

    public GenreJpaEntity(
            final String id,
            final String name,
            final String description,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.categories = new HashSet<>();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static GenreJpaEntity from(final Genre genre) {
        final var entity = new GenreJpaEntity(
                genre.getId().getValue(),
                genre.getName(),
                genre.getDescription(),
                genre.isActive(),
                genre.getCreatedAt(),
                genre.getUpdatedAt(),
                genre.getDeletedAt()
        );

        genre.getCategories().forEach(entity::addCategory);

        return entity;
    }

    public Genre toAggregate() {
        return new GenreBuilder(getName(), getDescription(),
                getCategories()
                        .stream()
                        .map(it -> CategoryId.from(it .getId().getCategoryId())).toList())
                .withId(GenreId.from(getId()))
                .withActive(isActive())
                .withCreatedAt(getCreatedAt())
                .withUpdatedAt(getUpdatedAt())
                .withDeletedAt(getDeletedAt())
                .build();
    }

    public void addCategory(final CategoryId categoryId) {
        this.categories.add(GenreCategoryJpaEntity.from(this, categoryId));
    }

    public void removeCategory(final CategoryId categoryId) {
        this.categories.remove(GenreCategoryJpaEntity.from(this, categoryId));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<GenreCategoryJpaEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<GenreCategoryJpaEntity> categories) {
        this.categories = categories;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }
}
