package com.jobee.admin.service.infrastructure.review.repository;

import com.jobee.admin.service.domain.review.ReviewRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<ReviewJpaEntity, String> {

    @Query(value = "SELECT c.id from Review c WHERE c.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);

    @Query("""
                SELECT a FROM Review a
                WHERE (:status IS NULL OR a.status = :status)
                AND (:userId IS NULL OR a.userId = :userId)
                AND (:productId IS NULL OR a.productId = :productId)
            """)
    List<ReviewJpaEntity> findBy(@Param("status") String status, @Param("userId") String userId, @Param("productId") String productId);

    @Query("""
                SELECT new com.jobee.admin.service.domain.review.ReviewRating(
                  COUNT(r),
                  SUM(CASE WHEN r.overallRating = 1 THEN 1 ELSE 0 END),
                  SUM(CASE WHEN r.overallRating = 2 THEN 1 ELSE 0 END),
                  SUM(CASE WHEN r.overallRating = 3 THEN 1 ELSE 0 END),
                  SUM(CASE WHEN r.overallRating = 4 THEN 1 ELSE 0 END),
                  SUM(CASE WHEN r.overallRating = 5 THEN 1 ELSE 0 END)
              )
              FROM Review r
              WHERE r.deletedAt IS NULL AND r.productId = :productId
            """)
    ReviewRating getReviewRatings(@Param("productId") String productId);
}

