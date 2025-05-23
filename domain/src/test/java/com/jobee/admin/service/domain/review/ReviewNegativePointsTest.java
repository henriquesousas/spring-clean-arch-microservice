package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ReviewNegativePointsTest {

    @Test
    public void giveAnActiveReview_whenAddNegativePoints_shouldUpdate() {
        final var expectedType = ProductType.PRODUCT;
        final var expectedSource = "any";
        final var expectedPoints = Set.of(Feedback.of("pos 1"), Feedback.of("pos 2"));

        final var expectedReview = new ReviewBuilder("some title", "any", UserId.unique(), expectedType, expectedSource,Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .build();

        expectedPoints.forEach(expectedReview::addNegativeFeedback);

        Assertions.assertEquals(expectedReview.getNegativeFeedbacks(), expectedPoints);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnActiveReview_whenRemoveNegativePoints_shouldUpdate() {
        final var expectedDefaultPoints = Set.of(Feedback.of("pos 1"), Feedback.of("pos 2"));
        final var expectedActualPoints = Set.of(Feedback.of("pos 2"));
        final var expectedType = ProductType.PRODUCT;
        final var expectedSource = "any";

        final var expectedReview = new ReviewBuilder("some title", "any", UserId.unique(),expectedType,  expectedSource,Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .withNegativePoints(expectedDefaultPoints)
                .build();

        Assertions.assertEquals(expectedReview.getNegativeFeedbacks(), expectedDefaultPoints);

        expectedReview.removeNegativeFeedback(Feedback.of("pos 1"));

        Assertions.assertFalse(expectedReview.getNotification().hasError());
        Assertions.assertEquals(expectedReview.getNegativeFeedbacks(), expectedActualPoints);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
