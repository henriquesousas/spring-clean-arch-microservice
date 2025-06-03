package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.review.valueobjects.FeedbackType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReviewRemoveFeedbackTest {

    @Test
    public void givenAnValidReview_whenRemovePositiveFeedback_shouldReturnEmpty() {

        final var expectedFeedbackValue = "I am ok";

        final var expectedReview = Fixture.reviewWithAllValues().build();

        expectedReview.addFeedback(expectedFeedbackValue, FeedbackType.PROS);
        expectedReview.removeFeedback(Feedback.from(expectedFeedbackValue), FeedbackType.PROS);

        Assertions.assertFalse(expectedReview.getNotification().hasError());
        Assertions.assertEquals(expectedReview.getPositiveFeedback().size(), 1);
        Assertions.assertEquals(expectedReview.getNegativeFeedback().size(), 1);
    }

    @Test
    public void givenAnValidReview_whenRemoveNegativeFeedback_shouldReturnEmpty() {

        final var expectedFeedbackValue = "I am ok";

        final var expectedReview = Fixture.reviewWithAllValues().build();

        expectedReview.addFeedback(expectedFeedbackValue, FeedbackType.CONS);
        expectedReview.removeFeedback(Feedback.from(expectedFeedbackValue), FeedbackType.CONS);

        Assertions.assertFalse(expectedReview.getNotification().hasError());
        Assertions.assertEquals(expectedReview.getPositiveFeedback().size(), 1);
        Assertions.assertEquals(expectedReview.getNegativeFeedback().size(), 1);
    }


}
