package com.jobee.admin.service.infrastructure.review;

import com.jobee.admin.service.application.events.DomainEventHandler;
import com.jobee.admin.service.application.usecases.review.create.CreateReviewUseCase;
import com.jobee.admin.service.application.events.DomainEventMediator;
import com.jobee.admin.service.application.handles.review.ReviewCreatedEventHandler;
import com.jobee.admin.service.application.usecases.review.retrieve.GetReviewByIdUseCase;
import com.jobee.admin.service.domain.review.ReviewRepository;
import com.jobee.admin.service.infrastructure.SpringIntegrationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ReviewProviders {

    @Autowired
    private ApplicationEventPublisher publisher;

    private final ReviewRepository repository;

    public ReviewProviders(ReviewRepository repository) {
        this.repository = repository;
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
}
