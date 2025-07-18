package br.com.opinai.api.gestao.produto.infrastructure.tags;

import br.com.opinai.api.gestao.produto.infrastructure.product.ProductJpaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Tags")
@Table(name = "tags")
public class TagJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<ProductJpaEntity> products = new HashSet<>();

}
