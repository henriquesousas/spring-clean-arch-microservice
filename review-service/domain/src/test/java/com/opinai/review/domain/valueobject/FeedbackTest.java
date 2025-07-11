package com.opinai.review.domain.valueobject;

import com.opinai.review.domain.UnitTest;
import com.opinai.review.domain.valueobjects.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FeedbackTest  extends UnitTest {

    @Test
    public void givenAnNullFeedback_whenInstantiate_shouldNoError() {
        final var expectedError = "Feedback inválido";
        final var feedback = Feedback.from(null);
        Assertions.assertTrue(feedback.getNotification().hasError());
        Assertions.assertEquals(feedback.getNotification().getErrors().size(),1);
        Assertions.assertEquals(feedback.getNotification().getFirstError().message(), expectedError);
    }

    @Test
    public void givenAValidFeedback_whenInstantiate_shouldHaveNoError() {
        final var expectedValue = "any";
        final var expectedFeedback = Feedback.from(expectedValue);
        Assertions.assertNotNull(expectedFeedback);
        Assertions.assertEquals(expectedFeedback.getValue(), expectedValue);
        Assertions.assertFalse(expectedFeedback.getNotification().hasError());
    }
}
