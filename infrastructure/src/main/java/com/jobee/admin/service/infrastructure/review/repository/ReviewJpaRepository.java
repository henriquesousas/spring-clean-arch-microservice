package com.jobee.admin.service.infrastructure.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<ReviewJpaEntity, String> {
    //    Page<ReviewModel> findAll(Specification<ReviewModel> whereClause, Pageable page);

    @Query(value = "select c.id from Review c where c.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);

    @Query("""
                SELECT a FROM Review a
                WHERE (:status IS NULL OR a.status = :status)
                AND (:userId IS NULL OR a.userId = :userId)
            """)
    List<ReviewJpaEntity> findBy(
            @Param("status") String status,
            @Param("userId") String userId
    );
}

