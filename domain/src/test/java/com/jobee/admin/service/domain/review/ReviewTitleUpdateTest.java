package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewTitleUpdateTest {

    @Test
    public void giveAnActiveReview_whenChangeTitle_shouldUpdate() {

        final var expectedActualTitle = "some title";
        final var expectedUpdatedTitle = "some title updated";
        final var expectedType = ProductType.PRODUCT;
        final var expectedSource = "any";
        final var expectedReview = new ReviewBuilder("some title", expectedActualTitle, UserId.unique(), expectedType, expectedSource,Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .build();

        Assertions.assertEquals(expectedReview.getTitle(), expectedActualTitle);

        expectedReview.updateTitle(expectedUpdatedTitle);

        Assertions.assertEquals(expectedReview.getTitle(), expectedUpdatedTitle);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
