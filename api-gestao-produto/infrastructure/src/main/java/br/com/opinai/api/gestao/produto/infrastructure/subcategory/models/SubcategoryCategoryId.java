package br.com.opinai.api.gestao.produto.infrastructure.subcategory.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubcategoryCategoryId implements Serializable {
    @Column(name = "subcategories_id", nullable = false)
    private String subcategoryId;

    @Column(name = "category_id", nullable = false)
    private String categoryId;

    public SubcategoryCategoryId() {
    }

    private SubcategoryCategoryId(final String subcategoryId, final String categoryId) {
        this.subcategoryId = subcategoryId;
        this.categoryId = categoryId;
    }

    public static SubcategoryCategoryId from(final String genreId, final String categoryId) {
        return new SubcategoryCategoryId(genreId, categoryId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubcategoryCategoryId that = (SubcategoryCategoryId) o;
        return Objects.equals(subcategoryId, that.subcategoryId) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subcategoryId, categoryId);
    }

    public String getGenreId() {
        return subcategoryId;
    }

    public void setGenreId(String genreId) {
        this.subcategoryId = genreId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
