package com.jobee.admin.service.infrastructure.genre.repository;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GenreCategoryId implements Serializable {
    @Column(name = "genre_id", nullable = false)
    private  String genreId;

    @Column(name = "category_id", nullable = false)
    private  String categoryId;

    public GenreCategoryId() {    }

    private GenreCategoryId(
            final String genreId,
            final String categoryId
    ) {
        this.genreId = genreId;
        this.categoryId = categoryId;
    }

    public static GenreCategoryId from(
            final String genreId,
            final String categoryId
    ) {
        return new GenreCategoryId(genreId, categoryId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreCategoryId that = (GenreCategoryId) o;
        return Objects.equals(genreId, that.genreId) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, categoryId);
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
