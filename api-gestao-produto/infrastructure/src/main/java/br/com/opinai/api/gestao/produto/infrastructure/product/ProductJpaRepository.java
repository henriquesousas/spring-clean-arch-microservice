package br.com.opinai.api.gestao.produto.infrastructure.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductJpaRepository  extends JpaRepository<ProductJpaEntity, String> {
    @EntityGraph(attributePaths = {"tags", "brand", "category", "subCategory", "photos"})
    Page<ProductJpaEntity> findAll(Specification<ProductJpaEntity> whereClause, Pageable page);

//    @Query("SELECT p FROM Products p LEFT JOIN FETCH p.tags WHERE p.id = :id")
    @EntityGraph(attributePaths = {"tags", "brand", "category", "subCategory", "photos"})
    @Query("SELECT p FROM Products p WHERE p.id = :id")
    Optional<ProductJpaEntity> findById(@Param("id") String id);

}
