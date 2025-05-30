package com.jobee.admin.service.infrastructure.review;

import com.jobee.admin.service.application.review.CreateReviewUseCase;
import com.jobee.admin.service.domain.review.ReviewRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewProviders {

    private final ReviewRepository repository;

    public ReviewProviders(ReviewRepository repository) {
        this.repository = repository;
    }

    @Bean
    public CreateReviewUseCase createReviewUseCase() {
        return new CreateReviewUseCase(repository);
    }
}
