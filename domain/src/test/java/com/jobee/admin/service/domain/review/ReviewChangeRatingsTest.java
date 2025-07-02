package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.UnitTest;
import com.jobee.admin.service.domain.review.enums.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewChangeRatingsTest  extends UnitTest {

    @Test
    public void giveAnActiveReview_whenChangeRating_shouldUpdate() {

        final var expectedReview = Fixture.reviewWithDefaultValues()
                .withActive(true)
                .build();

        Assertions.assertEquals(expectedReview.getRating().getValue(), Fixture.expectedRating.getValue());

        expectedReview.changeRating(Score.TWO);

        Assertions.assertEquals(expectedReview.getRating().getValue(),Score.TWO.getValue());
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
