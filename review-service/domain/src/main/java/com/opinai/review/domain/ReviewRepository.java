package com.opinai.review.domain;

import com.opinai.review.domain.valueobjects.ReviewId;
import com.opinai.shared.domain.pagination.Pagination;
import com.opinai.shared.domain.pagination.Search;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    void create(Review review);
    Review update(Review review);
    Optional<Review> findById(ReviewId id);
    Pagination<Review> findAll(Search query);
    List<Review> findBy(String status, String userId, String productId);
    ReviewRating getRatings(String productId);
    void delete(ReviewId id);
}
