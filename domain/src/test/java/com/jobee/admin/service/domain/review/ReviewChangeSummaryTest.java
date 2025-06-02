package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewChangeSummaryTest {

    @Test
    public void giveAnActiveReview_whenChangeComment_shouldUpdate() {

//        final var expectedActualComment = "some comment";
//        final var expectedUpdatedComment = "some comment updated";
//        final var expectedType = Type.PRODUCT;
//        final var expectedSource = "any";
//        final var expectedReview = new ReviewBuilder(
//                "some description",
//                expectedActualComment,
//                UserId.unique(),
//                expectedType,
//                expectedSource,
//                RatingScale.RA_1
//        )
//                .withActive(true)
//                .build();
//
//        Assertions.assertEquals(expectedReview.getSummary(), expectedActualComment);
//
//        expectedReview.changeSummary(expectedUpdatedComment);
//
//        Assertions.assertEquals(expectedReview.getSummary(), expectedUpdatedComment);
//        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
//        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnInActiveReview_whenChangeComment_shouldNotUpdate() {

//        final var expectedActualComment = "some comment updated";
//        final var expectedError = new Error("Os comentários não podem ser alterados para avaliações inativas");
//        final var expectedType = Type.PRODUCT;
//        final var expectedSource = "any";
//        final var expectedReview = new ReviewBuilder("some description", expectedActualComment, UserId.unique(),
//                expectedType, expectedSource, RatingScale.RA_1).build();
//
//        expectedReview.changeSummary(expectedActualComment);
//
//        Assertions.assertEquals(expectedReview.getSummary(), expectedActualComment);
//        Assertions.assertNull(expectedReview.getDeletedAt());
//        Assertions.assertTrue(expectedReview.getNotification().hasError());
//        Assertions.assertEquals(expectedReview.getNotification().getFirstError().message(), expectedError.getMessage());
    }
}
