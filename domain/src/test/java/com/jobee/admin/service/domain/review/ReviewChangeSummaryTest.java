package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewChangeSummaryTest {

    @Test
    public void giveAnActiveReview_whenChangeComment_shouldUpdate() {

        final var expectedUpdatedSummary = "summary updated";

        final var expectedReview = Fixture.reviewWithDefaultValues().withActive(true).build();

        Assertions.assertEquals(expectedReview.getSummary(), Fixture.expectedSummary);

        expectedReview.changeSummary(expectedUpdatedSummary);

        Assertions.assertEquals(expectedReview.getSummary(), expectedUpdatedSummary);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnInActiveReview_whenChangeComment_shouldNotUpdate() {

        final var expectedError = new Error("Os comentários não podem ser alterados para avaliações inativas");

        final var expectedReview = Fixture.reviewWithDefaultValues().build();

        expectedReview.changeSummary( "some summary updated");

        Assertions.assertEquals(expectedReview.getSummary(), Fixture.expectedSummary);
        Assertions.assertNull(expectedReview.getDeletedAt());
        Assertions.assertTrue(expectedReview.getNotification().hasError());
        Assertions.assertEquals(expectedReview.getNotification().getFirstError().message(), expectedError.getMessage());
    }
}
