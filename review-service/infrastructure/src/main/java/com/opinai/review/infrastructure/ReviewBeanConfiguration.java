package com.opinai.review.infrastructure;

import com.opinai.review.application.ApplicationService;
import com.opinai.review.application.handler.ReviewCreatedEventHandler;
import com.opinai.review.application.usecases.average.GetReviewAverageByProductIdUseCase;
import com.opinai.review.application.usecases.create.CreateReviewUseCase;
import com.opinai.review.application.usecases.delete.DeleteReviewUseCase;
import com.opinai.review.application.usecases.retrieve.getbyid.GetReviewByIdUseCase;
import com.opinai.review.application.usecases.retrieve.list.ListReviewUseCase;
import com.opinai.review.application.usecases.summary.GetReviewSummaryUseCase;
import com.opinai.review.application.usecases.update.UpdateReviewUseCase;
import com.opinai.review.domain.ReviewRepository;
import com.opinai.shared.domain.events.DomainEventMediator;
import com.opinai.shared.domain.events.handler.DomainEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Objects;

@Configuration
public class ReviewBeanConfiguration {

    @Autowired
    private ApplicationEventPublisher publisher;

    private final ReviewRepository repository;

    public ReviewBeanConfiguration(ReviewRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Bean
    public ApplicationService reviewApplicationService() {
        return new ApplicationService();
    }

    @Bean
    public CreateReviewUseCase createReviewUseCase() {
        List<DomainEventHandler> domainEventHandlers = List.of(new ReviewCreatedEventHandler());
        final var springIntegrationEventPublisher = SpringIntegrationEventPublisher.from(publisher);
        final var eventMediator = DomainEventMediator.from(springIntegrationEventPublisher, domainEventHandlers);
        return new CreateReviewUseCase(
                repository,
                eventMediator
        );
    }

    @Bean
    public GetReviewByIdUseCase getReviewByIdUseCase() {
        return new GetReviewByIdUseCase(repository);
    }

    @Bean
    public ListReviewUseCase listReviewUseCase() {
        return new ListReviewUseCase(repository);
    }

    @Bean
    public DeleteReviewUseCase deleteReviewUseCase() {
        return new DeleteReviewUseCase(repository);
    }

    @Bean
    public UpdateReviewUseCase updateReviewUseCase() {
        return new UpdateReviewUseCase(repository);
    }

    @Bean
    public GetReviewAverageByProductIdUseCase calculateReviewAvgUseCase(ApplicationService service) {
        return new GetReviewAverageByProductIdUseCase(repository, service);
    }

    @Bean
    public GetReviewSummaryUseCase getReviewSummaryUseCase(ApplicationService service) {
        return new GetReviewSummaryUseCase(repository, service);
    }
}
