package com.jobee.admin.service.infrastructure.reviewanalysis.repository;

import com.jobee.admin.service.domain.reviewanalysis.ReviewAnalysis;
import com.jobee.admin.service.domain.reviewanalysis.ReviewAnalysisId;
import com.jobee.admin.service.domain.reviewanalysis.ReviewAnalysisRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReviewAnalysisRepositoryMsql implements ReviewAnalysisRepository {

    private final ReviewAnalysisJpaRepository repository;

    public ReviewAnalysisRepositoryMsql(ReviewAnalysisJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(ReviewAnalysis review) {
        this.repository.save(ReviewAnalysisJpaModel.from(review));
    }

    @Override
    public ReviewAnalysis update(ReviewAnalysis review) {
        return null;
    }

    @Override
    public Optional<ReviewAnalysis> findById(ReviewAnalysisId id) {
        return Optional.empty();
    }

    @Override
    public void delete(ReviewAnalysisId id) {

    }
}
