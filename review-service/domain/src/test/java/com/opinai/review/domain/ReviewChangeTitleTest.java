package com.opinai.review.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewChangeTitleTest  extends UnitTest {

    @Test
    public void giveAnActiveReview_whenChangeTitle_shouldUpdate() {

        final var expectedUpdatedTitle = " title updated";

        final var expectedReview = Fixture.reviewWithDefaultValues()
                .withActive(true).build();


        Assertions.assertEquals(expectedReview.getTitle(), Fixture.expectedTitle);

        expectedReview.changeTitle(expectedUpdatedTitle);

        Assertions.assertEquals(expectedReview.getTitle(), expectedUpdatedTitle);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
