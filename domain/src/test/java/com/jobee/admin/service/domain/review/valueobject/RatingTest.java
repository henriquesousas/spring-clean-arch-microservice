package com.jobee.admin.service.domain.review.valueobject;

import com.jobee.admin.service.domain.review.enums.Score;
import com.jobee.admin.service.domain.review.valueobjects.Rating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RatingTest {

    @Test
    public void givenAValidRating_whenCreate_shouldCreateARating() {
        final var score =  Rating.from(Score.ONE);
        Assertions.assertEquals(1, score.getValue());
    }

    @Test
    public void givenANullRating_whenCreate_shouldHaveANotificationError() {
        final var expectedError = "É necessário informar a nota geral";

        final var score =  Rating.from(null);

        Assertions.assertEquals(1, score.getNotification().getErrors().size());
        Assertions.assertEquals(expectedError, score.getNotification().getFirstError().message());
        Assertions.assertNull( score.getValue());
    }
}
