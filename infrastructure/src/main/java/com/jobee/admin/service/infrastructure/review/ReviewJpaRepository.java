package com.jobee.admin.service.infrastructure.review;

import com.jobee.admin.service.infrastructure.review.models.ReviewJpaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepository extends JpaRepository<ReviewJpaModel, String> {
//    Page<ReviewModel> findAll(Specification<ReviewModel> whereClause, Pageable page);
//    @Query(value = "select g.id from Genre g where g.id in :ids")
//    List<String> existsByIds(@Param("ids") List<String> ids);
}

