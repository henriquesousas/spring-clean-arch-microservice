package com.opinai.review.application;

import br.com.opinai.api.review.application.ApplicationService;
import br.com.opinai.api.review.domain.ReviewRating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewApplicationServiceTest {

    @Test
    public void givenAValidCommand_whenCalculateAvg_shoudReturnAvg() {

        final var expectedAvg = 2.4;
        final var command = new ReviewRating(
                14L,
                5L,
                3L,
                3L,
                1L,
                2L
        );
        final var average = new ApplicationService().calculateAverage(command);
        Assertions.assertEquals(expectedAvg, average);
    }
}
