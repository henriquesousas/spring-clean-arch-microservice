package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewActivationTest {

    @Test
    public void giveAnActiveReview_whenDeactivate_shouldUpdateIsActiveToFalse() {

        final var expectedReview = Fixture.reviewWithDefaultValues().withActive(true).build();

        expectedReview.deactivate();

        Assertions.assertFalse(expectedReview.isActive());
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNotNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnInActiveReview_whenActivate_shouldUpdateIsActiveToTrue() {

        final var expectedReview =  Fixture.reviewWithDefaultValues().build();

        expectedReview.activate();

        Assertions.assertTrue(expectedReview.isActive());
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }
}
