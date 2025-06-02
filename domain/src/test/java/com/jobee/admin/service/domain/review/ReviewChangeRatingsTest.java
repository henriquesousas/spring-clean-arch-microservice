package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewChangeRatingsTest {

    @Test
    public void giveAnActiveReview_whenChangeRating_shouldUpdate() {
        final var expectedType = Type.PRODUCT;
        final var expectedSource = "any";
        final var expectedReview = new ReviewBuilder("some title", "any",
                UserId.unique(), expectedType, expectedSource,
                RatingScale.RA_1,
                null,
                null
        )
                .withActive(true)
                .build();

        Assertions.assertEquals(expectedReview.getRating().getOverall().getValue(), RatingScale.RA_1.getValue());
        Assertions.assertNull(expectedReview.getRating().getPostSale());
        Assertions.assertNull(expectedReview.getRating().getResponseTime());

        expectedReview.changeRating(RatingScale.RA_2, RatingScale.RA_2, RatingScale.RA_2);

        Assertions.assertEquals(expectedReview.getRating().getOverall().getValue(), RatingScale.RA_2.getValue());
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
