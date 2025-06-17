package com.jobee.admin.service.domain.analysis;


import java.util.Optional;

public interface ReviewAnalysisRepository {
    ReviewAnalysis create(ReviewAnalysis review);
    ReviewAnalysis update(ReviewAnalysis review);
    Optional<ReviewAnalysis> findById(ReviewAnalysisId id);
    void delete(ReviewAnalysisId id);
}
