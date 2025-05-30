package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.shared.validation.Error;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

public class ReviewIdTest {


    @Test
    public void giveAValidReviewId_whenInstantiated_thenItShouldBeValid() {
        // given
        final var expectedReviewId = "123e4567e89b12d3a456426614174000";

        // when
        final var reviewId = ReviewId.from(expectedReviewId);

        // then
        Assertions.assertEquals(expectedReviewId, reviewId.getValue());
        Assertions.assertFalse(reviewId.getNotification().hasError());
    }

    @Test
    public void giveAInValidUUID_whenInstantiated_thenItShouldBeInvalid() {
        // given
        final var expectedReviewId = "123e4567-e89b";
        final var expectedError = "ReviewId must be a valid UUID";

        // when
        final var reviewId = ReviewId.from(expectedReviewId);

        // then
        Assertions.assertTrue(reviewId.getNotification().hasError());
        Assertions.assertEquals(expectedError, reviewId.getNotification().getFirstError().message());
        Assertions.assertEquals(expectedReviewId, reviewId.getValue());
    }

    @Test
    public void giveAnEmptyUUID_whenInstantiated_thenItShouldBeInvalid() {
        // given
        final var expectedReviewId = "";
        final var expectedErrors= List.of(
                new Error("ReviewId cannot be null or empty"),
                new Error("ReviewId must be a valid UUID")
        );

        // when
        final var reviewId = ReviewId.from(expectedReviewId);

        // then

        Assertions.assertTrue(reviewId.getNotification().hasError());
        Assertions.assertEquals(expectedReviewId, reviewId.getValue());
        Assertions.assertEquals(
                new HashSet<>(reviewId.getNotification().getErrors()),
                new HashSet<>(expectedErrors)
        );
    }

}
