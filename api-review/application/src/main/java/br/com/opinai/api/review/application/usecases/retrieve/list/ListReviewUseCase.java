package br.com.opinai.api.review.application.usecases.retrieve.list;

import br.com.opinai.api.review.domain.Review;
import br.com.opinai.api.review.domain.ReviewRepository;
import br.com.opinai.api.review.domain.exceptions.ReviewNullableParamException;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
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
