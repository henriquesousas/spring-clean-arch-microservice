package com.jobee.admin.service.infrastructure.category.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, String> {

    Page<CategoryModel> findAll(Specification<CategoryModel> whereClause, Pageable page);
}
