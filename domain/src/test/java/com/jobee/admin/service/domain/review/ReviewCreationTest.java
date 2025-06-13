package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewCreationTest {

    @Test
    public void givenADefaultValue_whenInstantiate_shouldCreateANewReview() {

        final var expectedReview = Fixture.reviewWithDefaultValues().build();

        Assertions.assertEquals(expectedReview.getUserId(), Fixture.expectedUserId);
        Assertions.assertEquals(expectedReview.getTitle(), Fixture.expectedTitle);
        Assertions.assertEquals(expectedReview.getSummary(), Fixture.expectedSummary);
        Assertions.assertFalse(expectedReview.isActive());
        Assertions.assertNotNull(expectedReview.getCreatedAt());
        Assertions.assertNotNull(expectedReview.getUpdatedAt());
        Assertions.assertNull(expectedReview.getDeletedAt());
        Assertions.assertEquals(expectedReview.getRating().getOverall().getValue(), Fixture.expectedOverall.getValue());
        Assertions.assertNull(expectedReview.getRating().getPostSale());
        Assertions.assertNull(expectedReview.getRating().getResponseTime());
        Assertions.assertEquals(expectedReview.getStatus(), Status.PENDING);
        Assertions.assertEquals(expectedReview.getPositiveFeedback().size(), 0);
        Assertions.assertEquals(expectedReview.getNegativeFeedback().size(), 0);
        Assertions.assertEquals(expectedReview.getType(), Fixture.expectedType);
        Assertions.assertEquals(expectedReview.getBoughtFrom(), Fixture.expectedBoughtFrom);
        Assertions.assertNull(expectedReview.getRecommends());
        Assertions.assertNull(expectedReview.getUrl());
    }

    @Test
    public void givenAllValuesReview_whenInstantiate_shouldCreateANewReview() {

        final var expectedReview = Fixture.reviewWithAllValues().build();

        Assertions.assertEquals(expectedReview.getId(), Fixture.expectedReviewId);
        Assertions.assertEquals(expectedReview.getUserId(), Fixture.expectedUserId);
        Assertions.assertEquals(expectedReview.getTitle(), Fixture.expectedTitle);
        Assertions.assertEquals(expectedReview.getSummary(), Fixture.expectedSummary);
        Assertions.assertEquals(expectedReview.getCreatedAt(), Fixture.expectedCreatedAt);
        Assertions.assertEquals(expectedReview.getUpdatedAt(), Fixture.expectedUpdateAt);
        Assertions.assertEquals(expectedReview.getDeletedAt(), Fixture.expectedDeletedAt);
        Assertions.assertTrue(expectedReview.isActive());
        Assertions.assertEquals(expectedReview.getRating().getOverall().getValue(), Fixture.expectedOverall.getValue());
        Assertions.assertEquals(expectedReview.getRating().getResponseTime().getValue(),Fixture.expectedResponseTime.getValue());
        Assertions.assertEquals(expectedReview.getRating().getPostSale().getValue(), Fixture.expectedPostSale.getValue());
        Assertions.assertEquals(expectedReview.getStatus(), Fixture.expectedStatus);
        Assertions.assertEquals(expectedReview.getPositiveFeedback(), Fixture.expectedPositiveFeedback);
        Assertions.assertEquals(expectedReview.getNegativeFeedback(), Fixture.expectedNegativeFeedback);
        Assertions.assertEquals(expectedReview.getUrl().getValue(), Fixture.expectedUrl);
        Assertions.assertTrue(expectedReview.getRecommends());
        Assertions.assertTrue(expectedReview.isVerified());

    }
}
