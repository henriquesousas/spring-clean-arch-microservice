package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.UnitTest;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReviewAddFeedbackTest  extends UnitTest {

    @Test
    public void givenAnValidReview_whenAddNewPositiveFeedback_shouldSeeAllFeedbacks() {

        final var expectedFeedbackValue = "I am ok";
        final var expectedFeedbackList = Set.of(Feedback.from(expectedFeedbackValue));

        final var expectedReview = Fixture.reviewWithDefaultValues().withActive(true).build();

        expectedReview.addFeedback(expectedFeedbackValue, FeedbackType.PROS);

        Assertions.assertFalse(expectedReview.getNotification().hasError());
        Assertions.assertEquals(expectedReview.getPros().size(), 1);
        Assertions.assertEquals(expectedReview.getCons().size(), 0);
        Assertions.assertEquals(expectedReview.getPros(), expectedFeedbackList);
    }

    @Test
    public void givenAnValidReview_whenAddNewNegativeFeedback_shouldSeeAllFeedbacks() {

        final var expectedFeedbackValue = "I am ok";
        final var expectedFeedbackList = Set.of(Feedback.from(expectedFeedbackValue));

        final var expectedReview = Fixture.reviewWithDefaultValues().withActive(true).build();

        expectedReview.addFeedback(expectedFeedbackValue, FeedbackType.CONS);

        Assertions.assertFalse(expectedReview.getNotification().hasError());
        Assertions.assertEquals(expectedReview.getCons().size(), 1);
        Assertions.assertEquals(expectedReview.getPros().size(), 0);
        Assertions.assertEquals(expectedReview.getCons(), expectedFeedbackList);
    }

    @Test
    public void givenReviewWithInitialPositiveAndNegativeFeedback_whenAddNew_shouldSeeAllFeedbacks() {

        final var expectedFeedbackValue = "a new value";
        final var expectedPositiveFeedback = Stream.of(
                        Fixture.expectedPositiveFeedback,
                        Set.of(Feedback.from(expectedFeedbackValue))
                ).flatMap(Set::stream)
                .collect(Collectors.toSet());

        final var expectedNegativeFeedBack = Stream.of(
                        Fixture.expectedNegativeFeedback,
                        Set.of(Feedback.from(expectedFeedbackValue))
                ).flatMap(Set::stream)
                .collect(Collectors.toSet());


        final var expectedReview = Fixture.reviewWithAllValues().build();

        expectedReview.addFeedback(expectedFeedbackValue, FeedbackType.PROS);
        expectedReview.addFeedback(expectedFeedbackValue, FeedbackType.CONS);

        Assertions.assertFalse(expectedReview.getNotification().hasError());
        Assertions.assertEquals(expectedReview.getPros().size(), 2);
        Assertions.assertEquals(expectedReview.getCons().size(), 2);
        Assertions.assertEquals(expectedReview.getPros(), expectedPositiveFeedback);
        Assertions.assertEquals(expectedReview.getCons(), expectedNegativeFeedBack);
    }
}
