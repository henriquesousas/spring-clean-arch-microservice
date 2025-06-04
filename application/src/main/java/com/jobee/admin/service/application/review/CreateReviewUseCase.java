package com.jobee.admin.service.application.review;

import com.jobee.admin.service.application.UseCase;
import com.jobee.admin.service.domain.review.ReviewBuilder;
import com.jobee.admin.service.domain.review.ReviewRepository;
import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.exceptions.ValidationException;
import com.jobee.admin.service.domain.review.valueobjects.Url;
import com.jobee.admin.service.domain.utils.CollectionUtils;
import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import io.vavr.control.Either;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateReviewUseCase extends UseCase<CreateReviewInputDto, Either<DomainException, ReviewId>> {

    private static final Logger logger = LoggerFactory.getLogger(CreateReviewUseCase.class);
    private final ReviewRepository repository;

    public CreateReviewUseCase(final ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<DomainException, ReviewId> execute(final CreateReviewInputDto dto) {

        logger.info("Calls CreateReviewUseCase with dto {}", dto.toString());
        final var type = Type.of(dto.type());
        final var rating = RatingScale.of(dto.overallRating());


        if (type.isEmpty()) {
            return Either.left(ValidationException.with(new Error("Tipo de review desconhecido")));
        }

        if (rating.isEmpty()) {
            return Either.left(ValidationException.with(new Error("Você ainda não informou a nota geral")));
        }

        final var review = new ReviewBuilder(
                dto.title(),
                dto.summary(),
                UserId.from(dto.userId()),
                type.get(),
                dto.boughtFrom(),
                Url.from(dto.url()),
                rating.get(),
                RatingScale.of(dto.postSale()).orElse(null),
                RatingScale.of(dto.responseTime()).orElse(null),
                CollectionUtils.asSet(dto.positiveFeedback(),Feedback::from),
                CollectionUtils.asSet(dto.negativeFeedback(),Feedback::from)
        ).build();

        if (review.getNotification().hasError()) {
            final var errors = review.getNotification().getErrors();
            return Either.left(ValidationException.with(errors));
        }

        logger.info("Calls repository with dto {}", review.toString());
        this.repository.create(review);
        return Either.right(review.getId());
    }
}
