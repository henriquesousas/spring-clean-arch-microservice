package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewPositiveNoteTest {

    @Test
    public void giveAnActiveReview_whenAddPositiveNote_shouldUpdate() {
        final var expectedNote = "note1";
        final var expectedReview = new ReviewBuilder("some title", "any",
                UserId.unique(),  ProductType.PRODUCT, "any",
                Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .build();

        expectedReview.addPositiveNote(expectedNote);

        Assertions.assertTrue(expectedReview.getNotes().getPositives().contains(expectedNote));
        Assertions.assertFalse(expectedReview.getNotes().getNotification().hasError());
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnActiveReview_whenRemovePositivePoints_shouldUpdate() {

        final var expectedReview = new ReviewBuilder("some title", "any",
                UserId.unique(),
                ProductType.PRODUCT,
                "any",
                Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .build();


        expectedReview.addPositiveNote("Note1");
        expectedReview.addPositiveNote("Note2");

        Assertions.assertEquals(expectedReview.getNotes().getPositives().size(),2);

        expectedReview.removePositiveNote("Note1");

        System.out.println(expectedReview.getNotes().getNegatives().size());

        Assertions.assertEquals(expectedReview.getNotes().getPositives().size(),1);
        Assertions.assertTrue(expectedReview.getNotes().getPositives().contains("Note2"));
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
