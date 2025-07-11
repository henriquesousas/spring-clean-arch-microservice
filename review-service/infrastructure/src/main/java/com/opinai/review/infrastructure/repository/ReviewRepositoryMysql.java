package com.opinai.review.infrastructure.repository;

import com.opinai.review.domain.Review;
import com.opinai.review.domain.ReviewRating;
import com.opinai.review.domain.ReviewRepository;
import com.opinai.review.domain.valueobjects.ReviewId;
import com.opinai.review.infrastructure.repository.ReviewJpaEntity;
import com.opinai.review.infrastructure.repository.ReviewJpaRepository;
import com.opinai.shared.domain.pagination.Pagination;
import com.opinai.shared.domain.pagination.Search;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        this.repository.save(ReviewJpaEntity.from(review));
    }

    @Override
    public Review update(Review review) {
        final var model = this.repository.save(ReviewJpaEntity.from(review));
        return model.toAggregate();
    }

    @Override
    public Optional<Review> findById(ReviewId identifier) {
        return this.repository.findById(identifier.getValue())
                .map(ReviewJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Review> findAll(Search query) {
        return null;
    }

    @Override
    public List<Review> findBy(String status, String userId, String productId) {
        return this.repository.findBy(status, userId, productId)
                .stream()
                .map(ReviewJpaEntity::toAggregate)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewRating getRatings(String productId) {
      return this.repository.getReviewRatings(productId);
    }

    @Override
    public void delete(ReviewId identifier) {
        if (this.repository.existsById(identifier.getValue())) {
            this.repository.deleteById(identifier.getValue());
        }
    }
}
