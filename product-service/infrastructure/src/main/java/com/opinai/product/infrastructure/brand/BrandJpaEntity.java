package com.opinai.product.infrastructure.brand;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//TODOÇ Mudar de Data para Setter
@Data
@NoArgsConstructor
@Entity(name = "Brands")
@Table(name = "brands")
public class BrandJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;
}