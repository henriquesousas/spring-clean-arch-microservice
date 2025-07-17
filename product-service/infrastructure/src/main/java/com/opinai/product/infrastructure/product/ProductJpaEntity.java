package com.opinai.product.infrastructure.product;


import com.opinai.product.domain.product.*;
import com.opinai.product.infrastructure.brand.BrandJpaEntity;
import com.opinai.product.infrastructure.category.repository.CategoryModel;
import com.opinai.product.infrastructure.subcategory.repository.SubCategoryJpaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

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
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false, unique = true)
    private BrandJpaEntity brand;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, unique = true)
    private CategoryModel category;

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

    public Product toAggregate() {
        final var brand = new BrandRef(getBrand().getId(), getBrand().getName());
        final var category = new CategoryRef(getCategory().getId(), getCategory().getName());
        final var subcategory = new SubcategoryRef(getSubCategory().getId(), getSubCategory().getName());
        final var model = new Model(getModel());
        final var site = new Url(getSite());
        return new Product(
                ProductId.from(getId()),
                getName(),
                getDescription(),
                brand,
                model,
                category,
                subcategory,
                getColor(),
                null,
                null,
                site
        );
    }
}
