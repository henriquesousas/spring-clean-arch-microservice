package com.opinai.product.infrastructure.subcategory.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubcategoryJpaRepository extends JpaRepository<SubCategoryJpaEntity, String> {
    Page<SubCategoryJpaEntity> findAll(Specification<SubCategoryJpaEntity> whereClause, Pageable page);

    @Query(value = "select g.id from Subcategories g where g.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);
}
