package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ReviewPositivePointsTest {

    @Test
    public void giveAnActiveReview_whenAddPositivePoints_shouldUpdate() {
        final var expectedPoints = Set.of(Feedback.of("pos 1"), Feedback.of("pos 2"));
        final var expectedType = ProductType.PRODUCT;
        final var expectedSource = "any";

        final var expectedReview = new ReviewBuilder("some title", "any", UserId.unique(), expectedType, expectedSource,Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .build();

        expectedPoints.forEach(expectedReview::addPositiveFeedback);

        Assertions.assertEquals(expectedReview.getPositiveFeedbacks(), expectedPoints);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnActiveReview_whenRemovePositivePoints_shouldUpdate() {
        final var expectedType = ProductType.PRODUCT;
        final var expectedSource = "any";
        final var expectedDefaultPoints = Set.of(Feedback.of("pos 1"), Feedback.of("pos 2"));
        final var expectedActualPoints = Set.of(Feedback.of("pos 2"));

        final var expectedReview = new ReviewBuilder("some title", "any", UserId.unique(), expectedType, expectedSource,Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .withPositivePoints(expectedDefaultPoints)
                .build();

        Assertions.assertEquals(expectedReview.getPositiveFeedbacks(), expectedDefaultPoints);

        expectedReview.removePositiveFeedback(Feedback.of("pos 1"));

        Assertions.assertEquals(expectedReview.getPositiveFeedbacks(), expectedActualPoints);

        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
