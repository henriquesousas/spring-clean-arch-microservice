package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.UnitTest;
import com.jobee.admin.service.domain.review.enums.Status;
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
