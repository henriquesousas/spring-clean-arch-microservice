package com.jobee.admin.service.domain.analysis;

import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewAnalysisTest {

    @Test
    public void givenAValidValues_whenInstantiate_shouldHaveCorrectValues() {

        final var expectedUserId = UserId.unique();
        final var expectedReviewId =  ReviewId.unique();
        final var expectedStatus = Status.WAITING;
        final var expectedTypeAnalysis= TypeAnalysis.CREATE;

        final var sut =  new ReviewAnalysisBuilder(
                expectedUserId,
                expectedReviewId,
                expectedTypeAnalysis
        ).build();

        Assertions.assertEquals(expectedUserId, sut.getUserId());
        Assertions.assertEquals(expectedReviewId ,sut.getReviewId());
        Assertions.assertEquals(expectedTypeAnalysis,sut.getType());
        Assertions.assertEquals(expectedStatus,sut.getStatus());
        Assertions.assertNull(sut.getStartAt());
        Assertions.assertNull(sut.getEndAt());
        Assertions.assertNull(sut.getReason());
    }
}
