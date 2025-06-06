package com.jobee.admin.service.infrastructure.core.review;

import com.jobee.admin.service.domain.core.review.Review;
import com.jobee.admin.service.domain.core.review.ReviewRepository;
import com.jobee.admin.service.domain.core.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.pagination.Pagination;
import com.jobee.admin.service.domain.pagination.Search;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

//@Log4j2
@Component
public class ReviewRepositoryMysql implements ReviewRepository {

    private final ReviewJpaRepository repository;

    public ReviewRepositoryMysql(final ReviewJpaRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Transactional(rollbackOn = Exception.class)
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
