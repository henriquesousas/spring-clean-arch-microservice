package br.com.opinai.api.gestao.produto.infrastructure.photos;

import br.com.opinai.api.gestao.produto.infrastructure.product.ProductJpaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Photos")
@Table(name = "photos")
public class PhotoJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "url", columnDefinition = "TEXT", nullable = false)
    private String url;

    @Column(name = "alt_text", length = 255)
    private String altText;

    @Column(name = "is_main")
    private boolean main = false;

    @Column(name = "sort_order")
    private int sortOrder = 0;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_photo_product"))
    private ProductJpaEntity product;

}
