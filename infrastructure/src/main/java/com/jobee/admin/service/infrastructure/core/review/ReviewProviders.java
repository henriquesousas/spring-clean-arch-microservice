package com.jobee.admin.service.infrastructure.core.review;

import com.jobee.admin.service.application.DomainEventHandler;
import com.jobee.admin.service.application.IntegrationEventPublisher;
import com.jobee.admin.service.application.usecases.review.CreateReviewUseCase;
import com.jobee.admin.service.application.DomainEventMediatorPublisher;
import com.jobee.admin.service.application.handles.review.ReviewCreatedEventHandler;
import com.jobee.admin.service.domain.core.review.ReviewRepository;
import com.jobee.admin.service.infrastructure.IntegrationEventMediatorPublish;
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
        List<DomainEventHandler> handlers = List.of(new ReviewCreatedEventHandler());
        final var integrationMediator = IntegrationEventMediatorPublish.of(publisher);
        final var eventMediator = DomainEventMediatorPublisher.of(integrationMediator, handlers);
        return new CreateReviewUseCase(
                repository,
                eventMediator
        );
    }
}
