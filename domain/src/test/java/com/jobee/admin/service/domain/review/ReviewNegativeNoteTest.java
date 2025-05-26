package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ReviewNegativeNoteTest {

    @Test
    public void giveAnActiveReview_whenAddNegativePoints_shouldUpdate() {

        final var expectedReview = new ReviewBuilder("some title", "any",
                UserId.unique(), ProductType.PRODUCT, "any", Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .build();

        expectedReview.addNegativeNote("note 1");

        Assertions.assertEquals(expectedReview.getNotes().getNegatives().size(), 1);
        Assertions.assertTrue(expectedReview.getNotes().getNegatives().contains("note 1"));
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnActiveReview_whenRemoveNegativePoints_shouldUpdate() {

        final var expectedReview = new ReviewBuilder(
                "some title",
                "any",
                UserId.unique(),
                ProductType.PRODUCT,
                "any",
                Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .build();

        expectedReview.addNegativeNote("Note1");
        expectedReview.addNegativeNote("Note2");

        expectedReview.removeNegativeNote("Note1");

        Assertions.assertFalse(expectedReview.getNotification().hasError());
        Assertions.assertEquals(expectedReview.getNotes().getPositives().size(), 0);
        Assertions.assertEquals(expectedReview.getNotes().getNegatives().size(), 1);
        Assertions.assertTrue(expectedReview.getNotes().getNegatives().contains("Note2"));
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
