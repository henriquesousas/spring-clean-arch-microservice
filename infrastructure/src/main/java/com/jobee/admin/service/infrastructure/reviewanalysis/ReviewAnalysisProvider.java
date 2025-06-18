package com.jobee.admin.service.infrastructure.reviewanalysis;

import com.jobee.admin.service.application.usecases.analysis.CreateReviewAnalysisUseCase;
import com.jobee.admin.service.domain.analysis.ReviewAnalysisRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
public class ReviewAnalysisProvider {

    private final ReviewAnalysisRepository repository;

    public ReviewAnalysisProvider(ReviewAnalysisRepository repository) {
        this.repository = repository;
    }

    @Bean
    public CreateReviewAnalysisUseCase createReviewAnalysisUseCase() {
        return new CreateReviewAnalysisUseCase(repository);
    }
}
