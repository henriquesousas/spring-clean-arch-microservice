package br.com.opinai.api.review.application.usecases.create;

import br.com.opinai.api.review.domain.ReviewBuilder;
import br.com.opinai.api.review.domain.ReviewRepository;
import br.com.opinai.api.review.domain.enums.Score;
import br.com.opinai.api.review.domain.valueobjects.Feedback;
import br.com.opinai.api.review.domain.valueobjects.ReviewId;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.events.DomainEventMediator;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.ValidationException;
import com.opinai.shared.domain.utils.CollectionUtils;
import com.opinai.shared.domain.utils.EnumUtils;
import io.vavr.control.Either;


public class CreateReviewUseCase extends UseCase<CreateReviewInputCommand, Either<DomainException, ReviewId>> {

    private final ReviewRepository repository;
    private final DomainEventMediator domainEventMediator;

    public CreateReviewUseCase(final ReviewRepository repository, final DomainEventMediator publisher) {
        this.repository = repository;
        this.domainEventMediator = publisher;
    }

    @Override
    public Either<DomainException, ReviewId> execute(final CreateReviewInputCommand dto) {
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
