package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewChangeSummaryTest  extends UnitTest {

    @Test
    public void giveAnActiveReview_whenChangeComment_shouldUpdate() {

        final var expectedUpdatedSummary = "summary updated";

        final var expectedReview = Fixture.reviewWithDefaultValues().withActive(true).build();

        Assertions.assertEquals(expectedReview.getComment(), Fixture.expectedComment);

        expectedReview.changeSummary(expectedUpdatedSummary);

        Assertions.assertEquals(expectedReview.getComment(), expectedUpdatedSummary);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnInActiveReview_whenChangeComment_shouldNotUpdate() {

        final var expectedError = new Error("Os comentários não podem ser alterados para avaliações inativas");

        final var expectedReview = Fixture.reviewWithDefaultValues().build();

        expectedReview.changeSummary( "some summary updated");

        Assertions.assertEquals(expectedReview.getComment(), Fixture.expectedComment);
        Assertions.assertNull(expectedReview.getDeletedAt());
        Assertions.assertTrue(expectedReview.getNotification().hasError());
        Assertions.assertEquals(expectedReview.getNotification().getFirstError().message(), expectedError.getMessage());
    }
}
