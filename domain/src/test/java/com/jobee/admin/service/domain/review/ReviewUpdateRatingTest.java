package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//TODO: Add more scenarios
public class ReviewUpdateRatingTest {

    @Test
    public void giveAnActiveReview_whenChangeRating_shouldUpdate() {
        final var expectedType = ProductType.PRODUCT;
        final var expectedSource = "any";
        final var expectedReview = new ReviewBuilder("some title", "any",
                UserId.unique(), expectedType, expectedSource,Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .build();

        Assertions.assertEquals(expectedReview.getRating().getOverallRating(),RatingOptions.RA_1);
        Assertions.assertNull(expectedReview.getRating().getSupportResponseTimeRating());
        Assertions.assertNull(expectedReview.getRating().getAfterSalesServiceRating());

        expectedReview.updateOverallRating(RatingOptions.RA_2);

        Assertions.assertEquals(expectedReview.getRating().getOverallRating(), RatingOptions.RA_2);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
