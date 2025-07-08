package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.UnitTest;
import com.jobee.admin.service.domain.review.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewCreationTest  extends UnitTest {

    @Test
    public void givenADefaultValue_whenInstantiate_shouldCreateANewReview() {

        final var expectedReview = Fixture.reviewWithDefaultValues().build();

        Assertions.assertEquals(expectedReview.getUserId(), Fixture.expectedUserId);
        Assertions.assertEquals(expectedReview.getTitle(), Fixture.expectedTitle);
        Assertions.assertEquals(expectedReview.getComment(), Fixture.expectedComment);
        Assertions.assertFalse(expectedReview.isActive());
        Assertions.assertNotNull(expectedReview.getCreatedAt());
        Assertions.assertNotNull(expectedReview.getUpdatedAt());
        Assertions.assertNull(expectedReview.getDeletedAt());
        Assertions.assertEquals(expectedReview.getRating().getValue(), Fixture.expectedRating.getValue());
        Assertions.assertEquals(expectedReview.getStatus(), Status.PENDING);
        Assertions.assertEquals(expectedReview.getPros().size(), 0);
        Assertions.assertEquals(expectedReview.getCons().size(), 0);
        Assertions.assertEquals(expectedReview.getType(), Fixture.expectedType);
        Assertions.assertNull(expectedReview.getStore());
        Assertions.assertNull(expectedReview.getRecommends());
    }

    @Test
    public void givenAllValuesReview_whenInstantiate_shouldCreateANewReview() {

        final var expectedReview = Fixture.reviewWithAllValues().build();

        Assertions.assertEquals(expectedReview.getId(), Fixture.expectedReviewId);
        Assertions.assertEquals(expectedReview.getUserId(), Fixture.expectedUserId);
        Assertions.assertEquals(expectedReview.getTitle(), Fixture.expectedTitle);
        Assertions.assertEquals(expectedReview.getComment(), Fixture.expectedComment);
        Assertions.assertEquals(expectedReview.getCreatedAt(), Fixture.expectedCreatedAt);
        Assertions.assertEquals(expectedReview.getUpdatedAt(), Fixture.expectedUpdateAt);
        Assertions.assertEquals(expectedReview.getDeletedAt(), Fixture.expectedDeletedAt);
        Assertions.assertTrue(expectedReview.isActive());
        Assertions.assertEquals(expectedReview.getRating().getValue(), Fixture.expectedRating.getValue());
        Assertions.assertEquals(expectedReview.getStatus(), Fixture.expectedStatus);
        Assertions.assertEquals(expectedReview.getPros(), Fixture.expectedPositiveFeedback);
        Assertions.assertEquals(expectedReview.getCons(), Fixture.expectedNegativeFeedback);
        Assertions.assertTrue(expectedReview.getRecommends());
        Assertions.assertTrue(expectedReview.isVerifiedPurchase());
    }
}
