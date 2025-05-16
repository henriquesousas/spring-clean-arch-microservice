package com.jobee.admin.service.infrastructure.genre.repository;

import com.jobee.admin.service.domain.category.CategoryId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genres_categories")
public class GenreCategoryJpaEntity {

    @EmbeddedId
    private GenreCategoryId id;

    @ManyToOne
    @MapsId("genreId") // use o que esta em GenreCategoryId
    private GenreJpaEntity genre;

    public GenreCategoryJpaEntity() {
    }

    private GenreCategoryJpaEntity(final GenreJpaEntity genre, final CategoryId categoryId) {
        this.genre = genre;
        this.id = GenreCategoryId.from(genre.getId(), categoryId.getValue());
    }

    public static GenreCategoryJpaEntity from(final GenreJpaEntity genre, final CategoryId categoryId) {
        return new GenreCategoryJpaEntity(genre, categoryId);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final GenreCategoryJpaEntity that = (GenreCategoryJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public GenreCategoryId getId() {
        return id;
    }

    public void setId(GenreCategoryId id) {
        this.id = id;
    }

    public GenreJpaEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreJpaEntity genre) {
        this.genre = genre;
    }
}
