package com.jobee.admin.service.infrastructure.review;

import com.jobee.admin.service.application.ReviewApplicationService;
import com.jobee.admin.service.application.events.DomainEventHandler;
import com.jobee.admin.service.application.usecases.review.average.GetReviewAverageByProductIdUseCase;
import com.jobee.admin.service.application.usecases.review.create.CreateReviewUseCase;
import com.jobee.admin.service.application.events.DomainEventMediator;
import com.jobee.admin.service.application.handles.review.ReviewCreatedEventHandler;
import com.jobee.admin.service.application.usecases.review.delete.DeleteReviewUseCase;
import com.jobee.admin.service.application.usecases.review.retrieve.list.ListReviewUseCase;
import com.jobee.admin.service.application.usecases.review.retrieve.getbyid.GetReviewByIdUseCase;
import com.jobee.admin.service.application.usecases.review.update.UpdateReviewUseCase;
import com.jobee.admin.service.domain.review.ReviewRepository;
import com.jobee.admin.service.infrastructure.SpringIntegrationEventPublisher;
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
    public ReviewApplicationService reviewApplicationService() {
        return new ReviewApplicationService();
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
    public GetReviewAverageByProductIdUseCase calculateReviewAvgUseCase(ReviewApplicationService service) {
        return new GetReviewAverageByProductIdUseCase(repository, service);
    }
}
