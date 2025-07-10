package com.jobee.admin.service.application.usecases.review.retrieve.list;

import com.jobee.admin.service.application.usecases.UseCase;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.review.exceptions.ReviewNullableParamException;
import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.review.ReviewRepository;
import io.vavr.control.Either;

import java.util.List;
import java.util.Objects;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

public class ListReviewUseCase extends UseCase<ListReviewCommand, Either<DomainException, List<Review>>> {
    private final ReviewRepository repository;

    public ListReviewUseCase(ReviewRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<DomainException, List<Review>> execute(ListReviewCommand command) {
        if (command.status() == null && command.userId() == null && command.productId() == null) {
            return left(new ReviewNullableParamException());
        }

        return  right(this.repository.findBy(
                command.status(),
                command.userId(),
                command.productId()
        )) ;
    }
}
