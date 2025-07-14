package com.opinai.product.domain.genre;

import com.opinai.product.domain.category.CategoryId;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Getter
public class GenreBuilder {
    private GenreId genreId = GenreId.unique();
    private String name;
    private final String description;
    private final List<CategoryId> categories;
    private Boolean active = true;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    public GenreBuilder(String name, String description, List<CategoryId> categories) {
        this.name = Objects.requireNonNull(name, "'Name' should not be null");
        this.description = Objects.requireNonNull(description, "'Description' should not be null");
        this.categories = requireNonNullOrEmpty(categories);
    }

    public GenreBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public GenreBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    public GenreBuilder withCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public GenreBuilder withUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public GenreBuilder withDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public GenreBuilder withId(GenreId genreId) {
        this.genreId = genreId;
        return this;
    }

    public Genre build() {
        return Genre.newGenre(this);
    }

    private <T> List<T> requireNonNullOrEmpty(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("'Categories' should not be null or empty");
        }
        return list;
    }

}
