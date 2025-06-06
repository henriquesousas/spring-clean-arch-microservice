package com.jobee.admin.service.domain.core.review;

import com.jobee.admin.service.domain.core.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.pagination.Pagination;
import com.jobee.admin.service.domain.pagination.Search;

import java.util.Optional;

public interface ReviewRepository {
    void create(Review review);
    Review update(Review review);
    Optional<Review> findById(ReviewId id);
    Pagination<Review> findAll(Search query);
    void delete(ReviewId id);
}
