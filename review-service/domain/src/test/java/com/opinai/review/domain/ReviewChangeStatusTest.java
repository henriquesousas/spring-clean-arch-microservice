package com.opinai.review.domain;

import com.opinai.review.domain.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewChangeStatusTest  extends UnitTest {

    @Test
    public void giveAnActiveReview_whenChangeStatus_shouldUpdate() {

        final var expectedReview = Fixture.reviewWithDefaultValues().withActive(true).build();

        expectedReview.changeStatus(Status.APPROVED);

        Assertions.assertEquals(expectedReview.getStatus(), Status.APPROVED);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
