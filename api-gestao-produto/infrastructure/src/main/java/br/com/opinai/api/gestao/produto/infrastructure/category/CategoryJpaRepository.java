package br.com.opinai.api.gestao.produto.infrastructure.category;

import br.com.opinai.api.gestao.produto.infrastructure.category.models.CategoryJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, String> {
    Page<CategoryJpaEntity> findAll(Specification<CategoryJpaEntity> whereClause, Pageable page);

    @Query(value = "select c.id from category c where c.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);
}
