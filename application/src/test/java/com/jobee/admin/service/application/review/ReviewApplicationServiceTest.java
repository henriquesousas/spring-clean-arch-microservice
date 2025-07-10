package com.jobee.admin.service.application.review;

import com.jobee.admin.service.application.ReviewApplicationService;
import com.jobee.admin.service.domain.review.ReviewRating;
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
        final var average = new ReviewApplicationService().calculateAverage(command);
        Assertions.assertEquals(expectedAvg, average);
    }
}
