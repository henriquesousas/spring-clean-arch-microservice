package com.jobee.admin.service.infrastructure.review.repository;

import com.jobee.admin.service.domain.pagination.Pagination;
import com.jobee.admin.service.domain.pagination.Search;
import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.review.ReviewRepository;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.infrastructure.category.repository.CategoryModel;
import com.jobee.admin.service.infrastructure.review.models.ReviewJpaModel;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collections;
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
        this.repository.save(ReviewJpaModel.from(review));
    }

    @Override
    public Review update(Review review) {
        return null;
    }

    @Override
    public Optional<Review> findById(ReviewId identifier) {
        return this.repository.findById(identifier.getValue())
                .map(ReviewJpaModel::toAggregate);
    }

    @Override
    public Pagination<Review> findAll(Search query) {
        return null;
    }

    @Override
    public List<Review> findBy(String status, String userId) {
        return this.repository.findBy(status, userId)
                .stream()
                .map(ReviewJpaModel::toAggregate)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(ReviewId id) {

    }
}
