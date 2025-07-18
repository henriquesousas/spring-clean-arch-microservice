package br.com.opinai.api.gestao.produto.infrastructure.subcategory.models;

import br.com.opinai.api.gestao.produto.domain.category.CategoryId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "subcategories_categories")
public class SubcategoryCategoryJapEntity {

    @EmbeddedId
    private SubcategoryCategoryId id;

    // use property from GenreCategoryId (subcategoryId)
    @ManyToOne
    @MapsId("subcategoryId")
    @JoinColumn(name = "subcategories_id", foreignKey = @ForeignKey(name = "fk_subcategories_id"))
    private SubCategoryJpaEntity subcategoryId;

    public SubcategoryCategoryJapEntity() {
    }

    private SubcategoryCategoryJapEntity(final SubCategoryJpaEntity subcategoryId, final CategoryId categoryId) {
        this.subcategoryId = subcategoryId;
        this.id = SubcategoryCategoryId.from(subcategoryId.getId(), categoryId.getValue());
    }

    public static SubcategoryCategoryJapEntity from(final SubCategoryJpaEntity genre, final CategoryId categoryId) {
        return new SubcategoryCategoryJapEntity(genre, categoryId);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SubcategoryCategoryJapEntity that = (SubcategoryCategoryJapEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
