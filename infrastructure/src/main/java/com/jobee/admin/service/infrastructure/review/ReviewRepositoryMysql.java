package com.jobee.admin.service.infrastructure.review;

import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.review.ReviewRepository;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.pagination.Pagination;
import com.jobee.admin.service.domain.pagination.Search;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ReviewRepositoryMysql  implements ReviewRepository {

    private final ReviewJpaRepository repository;

    public ReviewRepositoryMysql(final ReviewJpaRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void create(Review review) {
        System.out.println("Created...");
       this.repository.save(ReviewJpaModel.from(review));
    }

    @Override
    public Review update(Review review) {
        return null;
    }

    @Override
    public Optional<Review> findById(ReviewId id) {
        return Optional.empty();
    }

    @Override
    public Pagination<Review> findAll(Search query) {
        return null;
    }

    @Override
    public void delete(ReviewId id) {

    }
}
