package com.jobee.admin.service.domain.reviewanalysis;

import com.jobee.admin.service.domain.UnitTest;
import com.jobee.admin.service.domain.utils.InstantUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewAnalysisTest  extends UnitTest {

    @Test
    public void givenAValidValues_whenInstantiate_shouldHaveCorrectValues() {

        final var expectedReviewId = "2f9d7582-d216-4f43-b9ce-14ed2b4903b1";
        final var expectedUserId = "aad74d08-dad7-43f0-8818-2f15cc1cea7b";
        final var expectedStatus = Status.WAITING;
        final var expectedTypeAnalysis = TypeAnalysis.CREATE;

        final var sut = new ReviewAnalysisBuilder(
                expectedUserId,
                expectedReviewId,
                expectedTypeAnalysis
        ).build();

        Assertions.assertNull(sut.getModeratorId());
        Assertions.assertEquals(expectedUserId, sut.getUserId());
        ;
        Assertions.assertEquals(expectedReviewId, sut.getReviewId());
        Assertions.assertNotNull(sut.getAggregateId());
        Assertions.assertEquals(expectedTypeAnalysis, sut.getType());
        Assertions.assertEquals(expectedStatus, sut.getStatus());
        Assertions.assertNull(sut.getStartAt());
        Assertions.assertNull(sut.getEndAt());
        Assertions.assertNull(sut.getReason());
    }

    @Test
    public void givenAValidReviewAnalysis_whenInitialize_shouldChangeStatusTOWaiting() {

        final var expectedReviewId = "2f9d7582d2164f43b9ce-14ed2b4903b1";
        final var expectedUserId = "aad74d08dad743f088182f15cc1cea7b";
        final var expectedModeratorId = "40dbf22dde0c40a687e1acc7bad1c81e";
        final var expectedStatus = Status.IN_ANALYSIS;
        final var expectedTypeAnalysis = TypeAnalysis.CREATE;

        final var sut = new ReviewAnalysisBuilder(
                expectedUserId,
                expectedReviewId,
                expectedTypeAnalysis
        ).build();

        sut.startAnalysis(expectedModeratorId);

        Assertions.assertEquals(expectedModeratorId, sut.getModeratorId());
        Assertions.assertEquals(expectedReviewId, sut.getReviewId());
        Assertions.assertEquals(expectedTypeAnalysis, sut.getType());
        Assertions.assertEquals(expectedStatus, sut.getStatus());
        Assertions.assertNotNull(sut.getStartAt());
        Assertions.assertNull(sut.getEndAt());
        Assertions.assertNull(sut.getReason());
    }

    @Test
    public void givenAValidReviewAnalysis_whenFinalize_shouldChangeStatus() {

        final var expectedReviewId = "2f9d7582d2164f43b9ce-14ed2b4903b1";
        final var expectedUserId = "aad74d08dad743f088182f15cc1cea7b";
        final var expectedModeratorId = "40dbf22dde0c40a687e1acc7bad1c81e";
        final var expectedStatus = Status.APPROVED;
        final var expectedTypeAnalysis = TypeAnalysis.CREATE;
        final var expectedReason = Reason.from("Tudo ok");

        final var sut = new ReviewAnalysisBuilder(
                expectedUserId,
                expectedReviewId,
                expectedTypeAnalysis
        ).build();

        sut.startAnalysis(expectedModeratorId);
        sut.endAnalysis(expectedStatus, expectedReason.getValue());

        Assertions.assertEquals(expectedModeratorId, sut.getModeratorId());
        Assertions.assertEquals(expectedUserId, sut.getUserId());
        Assertions.assertEquals(expectedReviewId, sut.getReviewId());
        Assertions.assertEquals(expectedTypeAnalysis, sut.getType());
        Assertions.assertEquals(expectedStatus, sut.getStatus());
        Assertions.assertNotNull(sut.getStartAt());
        Assertions.assertNotNull(sut.getEndAt());
        Assertions.assertEquals(expectedReason, sut.getReason());
    }

    @Test
    public void givenNotStartedAnalysis_whenTryEndAnalysis_shouldNotifyAnError() {

        final var expectedReviewId = "2f9d7582-d216-4f43-b9ce-14ed2b4903b1";
        final var expectedUserId = "aad74d08-dad7-43f0-8818-2f15cc1cea7b";
        final var expectedErrorMessage = "Análise ainda não foi iniciada.";
        final var expectedStatus = Status.WAITING;
        final var expectedTypeAnalysis = TypeAnalysis.CREATE;
        final var expectedReason = Reason.from("Tudo ok");

        final var sut = new ReviewAnalysisBuilder(
                expectedUserId,
                expectedReviewId,
                expectedTypeAnalysis
        ).build();

        sut.endAnalysis(expectedStatus, expectedReason.getValue());

        Assertions.assertTrue(sut.getNotification().hasError());
        Assertions.assertEquals(expectedErrorMessage, sut.getNotification().getFirstError().message());
        Assertions.assertEquals(expectedReviewId, sut.getReviewId());
        Assertions.assertEquals(expectedUserId, sut.getUserId());
        Assertions.assertEquals(expectedTypeAnalysis, sut.getType());
        Assertions.assertEquals(expectedStatus, sut.getStatus());
        Assertions.assertNull(sut.getModeratorId());
        Assertions.assertNull(sut.getStartAt());
        Assertions.assertNull(sut.getEndAt());
        Assertions.assertNull(sut.getReason());
    }

    @Test
    public void givenAnReviewApproved_whenTryStart_shouldNotifyAnError() {

        final var expectedReviewId = "2f9d7582d2164f43b9ce14ed2b4903b1";
        final var expectedUserId = "aad74d08dad743f088182f15cc1cea7b";
        final var expectedModeratorId = "40dbf22dde0c40a687e1acc7bad1c81e";
        final var expectedErrorMessage = "Análise ja foi iniciada.";
        final var expectedStartAt = InstantUtils.now();
        final var expectedEndAt = InstantUtils.now();
        final var expectedStatus = Status.APPROVED;
        final var expectedTypeAnalysis = TypeAnalysis.CREATE;
        final var expectedReason = "any reason";

        final var sut = new ReviewAnalysisBuilder(
                expectedUserId,
                expectedReviewId,
                expectedTypeAnalysis
        )
                .withModeratorId(expectedModeratorId)
                .withStartAt(expectedStartAt)
                .withEndAt(expectedEndAt)
                .withReason(expectedReason)
                .withStatus(expectedStatus)
                .build();

        sut.startAnalysis(expectedModeratorId);

        Assertions.assertTrue(sut.getNotification().hasError());
        Assertions.assertEquals(expectedErrorMessage, sut.getNotification().getFirstError().message());
        Assertions.assertEquals(expectedReviewId, sut.getReviewId());
        Assertions.assertEquals(expectedTypeAnalysis, sut.getType());
        Assertions.assertEquals(expectedStatus, sut.getStatus());
        Assertions.assertEquals(expectedModeratorId, sut.getModeratorId());
        Assertions.assertEquals(expectedStartAt, sut.getStartAt());
        Assertions.assertEquals(expectedEndAt, sut.getEndAt());
        Assertions.assertEquals(expectedReason, sut.getReason().getValue());
    }
}
