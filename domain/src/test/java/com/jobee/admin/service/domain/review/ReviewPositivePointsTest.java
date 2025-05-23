package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.review.valueobjects.ReviewPoint;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ReviewPositivePointsTest {

    @Test
    public void giveAnActiveReview_whenAddPositivePoints_shouldUpdate() {
        final var expectedPoints = Set.of(ReviewPoint.of("pos 1"), ReviewPoint.of("pos 2"));
        final var expectedType = ProductType.PRODUCT;
        final var expectedSource = "any";

        final var expectedReview = new ReviewBuilder("some title", "any", UserId.unique(), expectedType, expectedSource,Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .build();

        expectedPoints.forEach(expectedReview::addPositivePoint);

        Assertions.assertEquals(expectedReview.getPositivePoints(), expectedPoints);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnActiveReview_whenRemovePositivePoints_shouldUpdate() {
        final var expectedType = ProductType.PRODUCT;
        final var expectedSource = "any";
        final var expectedDefaultPoints = Set.of(ReviewPoint.of("pos 1"), ReviewPoint.of("pos 2"));
        final var expectedActualPoints = Set.of(ReviewPoint.of("pos 2"));

        final var expectedReview = new ReviewBuilder("some title", "any", UserId.unique(), expectedType, expectedSource,Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .withPositivePoints(expectedDefaultPoints)
                .build();

        Assertions.assertEquals(expectedReview.getPositivePoints(), expectedDefaultPoints);

        expectedReview.removePositivePoint(ReviewPoint.of("pos 1"));

        Assertions.assertEquals(expectedReview.getPositivePoints(), expectedActualPoints);

        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
