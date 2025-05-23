package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewActivationTest {

    @Test
    public void giveAnActiveReview_whenDeactivate_shouldUpdateIsActiveToFalse() {

        final var expectedReview = new ReviewBuilder("some description", "some comment", UserId.unique(),
                ProductType.SERVICE,
                "source", Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .build();

        expectedReview.deactivate();
        Assertions.assertFalse(expectedReview.isActive());
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNotNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnInActiveReview_whenActivate_shouldUpdateIsActiveToTrue() {

        final var expectedReview = new ReviewBuilder("some description", "some comment", UserId.unique(),
                ProductType.SERVICE,
                "source",Rating.newRating(RatingOptions.RA_1)).build();

        expectedReview.activate();

        Assertions.assertTrue(expectedReview.isActive());
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
