<<<<<<<< HEAD:product-service/infrastructure/src/main/java/com/opinai/product/infrastructure/category/repository/CategoryJpaRepository.java
package com.opinai.product.infrastructure.category.repository;

========
package br.com.opinai.api.gestao.produto.infrastructure.category;

import br.com.opinai.api.gestao.produto.infrastructure.category.models.CategoryJpaEntity;
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/main/java/br/com/opinai/api/gestao/produto/infrastructure/category/CategoryJpaRepository.java
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

<<<<<<<< HEAD:product-service/infrastructure/src/main/java/com/opinai/product/infrastructure/category/repository/CategoryJpaRepository.java
public interface CategoryJpaRepository extends JpaRepository<CategoryModel, String> {
    Page<CategoryModel> findAll(Specification<CategoryModel> whereClause, Pageable page);
========
public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, String> {
    Page<CategoryJpaEntity> findAll(Specification<CategoryJpaEntity> whereClause, Pageable page);
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/main/java/br/com/opinai/api/gestao/produto/infrastructure/category/CategoryJpaRepository.java

    @Query(value = "select c.id from category c where c.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);
}
