package br.com.opinai.api.gestao.produto.domain.product;

import br.com.opinai.api.gestao.produto.domain.product.ref.*;
import com.opinai.shared.domain.AggregateRoot;
import com.opinai.shared.domain.validation.ValidationHandler;
import lombok.Getter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
public class Product extends AggregateRoot<ProductId> {

    private String name;
    private String description;
    private BrandRef brandRef;
    private Model model;
    private CategoryRef categoryRef;
    private SubcategoryRef subcategoryRef;
    private String color;
    private Set<PhotosRef> photos;
    private Set<TagRef> tags;
    private Url site;

    public Product(
            final ProductId productId,
            final String name,
            final String description,
            final BrandRef brandRef,
            final Model model,
            final CategoryRef categoryRef,
            final SubcategoryRef subcategoryRef,
            final String color,
            final Set<PhotosRef> photos,
            final Set<TagRef> tags,
            final Url site
    ) {
        super(productId);
        this.name = name;
        this.description = description;
        this.brandRef = brandRef;
        this.model = model;
        this.categoryRef = categoryRef;
        this.subcategoryRef = subcategoryRef;
        this.color = color;
        this.photos = Objects.requireNonNullElse(photos, new HashSet<>());
        this.tags = Objects.requireNonNullElse(tags, new HashSet<>());
        this.site= site;

    }

    @Override
    public void validate(ValidationHandler handler) {
        //TODO: implement
    }
}
