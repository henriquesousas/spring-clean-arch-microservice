package com.jobee.admin.service.domain.reviewanalysis;


import java.util.Optional;

public interface ReviewAnalysisRepository {
    void create(ReviewAnalysis review);
    ReviewAnalysis update(ReviewAnalysis review);
    Optional<ReviewAnalysis> findById(ReviewAnalysisId id);
    void delete(ReviewAnalysisId id);
}
