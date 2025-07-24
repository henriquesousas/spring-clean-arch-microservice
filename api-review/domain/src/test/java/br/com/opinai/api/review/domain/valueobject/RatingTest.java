package br.com.opinai.api.review.domain.valueobject;

import br.com.opinai.api.review.domain.enums.Score;
import br.com.opinai.api.review.domain.valueobjects.Rating;
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
