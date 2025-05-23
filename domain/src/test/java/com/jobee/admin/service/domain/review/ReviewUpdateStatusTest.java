package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.review.enums.ReviewStatus;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewUpdateStatusTest {

    @Test
    public void giveAnActiveReview_whenChangeStatus_shouldUpdate() {
        final var expectedType = ProductType.PRODUCT;
        final var expectedSource = "any";
        final var expectedReview = new ReviewBuilder("some title", "any", UserId.unique(), expectedType, expectedSource, Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .build();

        expectedReview.updateReviewStatus(ReviewStatus.APPROVED);

        Assertions.assertEquals(expectedReview.getStatus(), ReviewStatus.APPROVED);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
