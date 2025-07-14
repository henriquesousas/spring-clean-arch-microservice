package com.opinai.reviewanalyze.infrastructure;

import com.opinai.reviewanalyze.application.CreateReviewAnalysisUseCase;
import com.opinai.reviewanalyze.domain.ReviewAnalysisRepository;
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
