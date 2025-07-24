package br.com.opinai.api.review.analise.infrastructure;

import br.com.opinai.api.review.analise.application.CreateReviewAnalysisUseCase;
import br.com.opinai.api.review.analise.domain.ReviewAnalysisRepository;
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
