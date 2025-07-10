package com.jobee.admin.service.application.usecases.review.create;

import com.jobee.admin.service.application.events.DomainEventMediator;
import com.jobee.admin.service.application.usecases.UseCase;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.exceptions.ValidationException;
import com.jobee.admin.service.domain.review.ReviewBuilder;
import com.jobee.admin.service.domain.review.ReviewRepository;
import com.jobee.admin.service.domain.review.enums.Score;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.utils.CollectionUtils;
import com.jobee.admin.service.domain.utils.EnumUtils;
import io.vavr.control.Either;


//TODO: Fix
public class CreateReviewUseCase extends UseCase<CreateReviewInputCommand, Either<DomainException, ReviewId>> {

    //    private static final Logger logger = LoggerFactory.getLogger(CreateReviewUseCase.class);
    private final ReviewRepository repository;
    private final DomainEventMediator domainEventMediator;

    public CreateReviewUseCase(final ReviewRepository repository, final DomainEventMediator publisher) {
        this.repository = repository;
        this.domainEventMediator = publisher;
    }

    @Override
    public Either<DomainException, ReviewId> execute(final CreateReviewInputCommand dto) {
        //logger.info("Calls CreateReviewUseCase with dto {}", dto.toString());
        final var review = ReviewBuilder.create(
                        dto.userId(),
                        dto.productId(),
                        EnumUtils.of(Score.values(), dto.rating()),
                        dto.title(),
                        dto.comment(),
                        CollectionUtils.asSet(dto.pros(), Feedback::from),
                        CollectionUtils.asSet(dto.cons(), Feedback::from)
                )
                .withRecommend(dto.recommends())
                .withStore(dto.store())
                .build();


        if (review.getNotification().hasError()) {
            final var errors = review.getNotification().getErrors();
            return Either.left(ValidationException.with(errors));
        }

        this.repository.create(review);
        this.domainEventMediator.publishEvent(review);

        return Either.right(review.getId());
    }
}
