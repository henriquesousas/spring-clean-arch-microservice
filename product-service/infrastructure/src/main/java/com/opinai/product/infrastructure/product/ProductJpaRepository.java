package com.opinai.product.infrastructure.product;

import com.opinai.product.infrastructure.category.repository.CategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository  extends JpaRepository<ProductJpaEntity, String> {
    Page<ProductJpaEntity> findAll(Specification<ProductJpaEntity> whereClause, Pageable page);
}
