package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.pagination.Pagination;
import com.jobee.admin.service.domain.pagination.Search;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;

import java.util.Optional;

public interface ReviewRepository {
    void create(Review review);
    Review update(Review review);
    Optional<Review> findById(ReviewId id);
    Pagination<Review> findAll(Search query);
    void delete(ReviewId id);
}
