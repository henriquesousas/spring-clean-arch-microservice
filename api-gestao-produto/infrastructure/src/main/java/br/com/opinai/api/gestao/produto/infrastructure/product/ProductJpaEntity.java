package br.com.opinai.api.gestao.produto.infrastructure.product;


import br.com.opinai.api.gestao.produto.domain.product.*;
import br.com.opinai.api.gestao.produto.domain.product.ref.BrandRef;
import br.com.opinai.api.gestao.produto.domain.product.ref.CategoryRef;
import br.com.opinai.api.gestao.produto.domain.product.ref.SubcategoryRef;
import br.com.opinai.api.gestao.produto.domain.product.ref.TagRef;
import br.com.opinai.api.gestao.produto.infrastructure.brand.BrandJpaEntity;
import br.com.opinai.api.gestao.produto.infrastructure.category.models.CategoryJpaEntity;
import br.com.opinai.api.gestao.produto.infrastructure.subcategory.models.SubCategoryJpaEntity;
import br.com.opinai.api.gestao.produto.infrastructure.tags.TagJpaEntity;
import com.opinai.shared.domain.utils.NullableUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Products")
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "model", nullable = false)
    private String model;

    @OneToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", unique = true)
    private BrandJpaEntity brand;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, unique = true)
    private CategoryJpaEntity category;

    @OneToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id", nullable = false, unique = true)
    private SubCategoryJpaEntity subCategory;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "site")
    private String site;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    @ManyToMany()
    @JoinTable(
            name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagJpaEntity> tags = new HashSet<>();

    public Product toAggregate() {
        //TODO: Criar builder
        return new Product(
                ProductId.from(getId()),
                getName(),
                getDescription(),
                NullableUtils.mapOrNull(this.getBrand(), entity -> BrandRef.with(entity.getId(), entity.getName())),
                new Model(getModel()),
                new CategoryRef(getCategory().getId(), getCategory().getName()),
                new SubcategoryRef(getSubCategory().getId(), getSubCategory().getName()),
                getColor(),
                null,
                getTags().stream().map(tag -> TagRef.with(tag.getId(), tag.getName())).collect(Collectors.toSet()),
                new Url(getSite())
        );
    }
}
