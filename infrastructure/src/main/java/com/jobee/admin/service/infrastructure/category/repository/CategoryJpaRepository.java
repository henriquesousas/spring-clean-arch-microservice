package com.jobee.admin.service.infrastructure.category.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryJpaRepository extends JpaRepository<CategoryModel, String> {
    Page<CategoryModel> findAll(Specification<CategoryModel> whereClause, Pageable page);

    @Query(value = "select c.id from category c where c.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);
}
