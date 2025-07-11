package com.opinai.review.domain;

import com.opinai.review.domain.enums.FeedbackType;
import com.opinai.review.domain.valueobjects.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewRemoveFeedbackTest  extends UnitTest {

    @Test
    public void givenAnValidReview_whenRemovePositiveFeedback_shouldReturnEmpty() {

        final var expectedFeedbackValue = "I am ok";

        final var expectedReview = Fixture.reviewWithAllValues().build();

        expectedReview.addFeedback(expectedFeedbackValue, FeedbackType.PROS);
        expectedReview.removeFeedback(Feedback.from(expectedFeedbackValue), FeedbackType.PROS);

        Assertions.assertFalse(expectedReview.getNotification().hasError());
        Assertions.assertEquals(expectedReview.getPros().size(), 1);
        Assertions.assertEquals(expectedReview.getCons().size(), 1);
    }

    @Test
    public void givenAnValidReview_whenRemoveNegativeFeedback_shouldReturnEmpty() {

        final var expectedFeedbackValue = "I am ok";

        final var expectedReview = Fixture.reviewWithAllValues().build();

        expectedReview.addFeedback(expectedFeedbackValue, FeedbackType.CONS);
        expectedReview.removeFeedback(Feedback.from(expectedFeedbackValue), FeedbackType.CONS);

        Assertions.assertFalse(expectedReview.getNotification().hasError());
        Assertions.assertEquals(expectedReview.getPros().size(), 1);
        Assertions.assertEquals(expectedReview.getCons().size(), 1);
    }


}
