package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//TODO: Add more scenarios
public class ReviewChangeRatingsTest {

    @Test
    public void giveAnActiveReview_whenChangeRating_shouldUpdate() {
        final var expectedType = Type.PRODUCT;
        final var expectedSource = "any";
        final var expectedReview = new ReviewBuilder("some title", "any",
                UserId.unique(), expectedType, expectedSource,
                RatingScale.RA_1)
                .withActive(true)
                .build();

        Assertions.assertEquals(expectedReview.getRatings().overallRating(),RatingScale.RA_1.getValue());
        Assertions.assertNull(expectedReview.getRatings().supportRating());
        Assertions.assertNull(expectedReview.getRatings().afterSalesRating());

        expectedReview.changeOverallRating(RatingScale.RA_2);

        Assertions.assertEquals(expectedReview.getRatings().overallRating(), RatingScale.RA_2.getValue());
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
