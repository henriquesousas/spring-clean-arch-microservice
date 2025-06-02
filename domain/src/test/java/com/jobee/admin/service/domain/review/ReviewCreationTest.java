package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.utils.InstantUtils;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ReviewCreationTest {

    @Test
    public void givenADefaultValue_whenInstantiate_shouldCreateANewReview() {

//        final var expectedUserId = UserId.unique();
//        final var expectedTitle = "some description";
//        final var expectedComment = "some comment";
//        final var expectedType = Type.PRODUCT;
//        final var expectedSource = "any";
//
//        final var expectedReview = new ReviewBuilder(
//                expectedTitle,
//                expectedComment,
//                expectedUserId,
//                expectedType,
//                expectedSource,
//                RatingScale.RA_1
//        ).build();
//
//        Assertions.assertEquals(expectedReview.getUserId(), expectedUserId);
//        Assertions.assertEquals(expectedReview.getTitle(), expectedTitle);
//        Assertions.assertEquals(expectedReview.getSummary(), expectedComment);
//        Assertions.assertFalse(expectedReview.isActive());
//        Assertions.assertNotNull(expectedReview.getCreatedAt());
//        Assertions.assertNotNull(expectedReview.getUpdatedAt());
//        Assertions.assertNull(expectedReview.getDeletedAt());
//        Assertions.assertEquals(expectedReview.getRatings().overallRating(), RatingScale.RA_1.getValue());
//        Assertions.assertNull(expectedReview.getRatings().supportRating());
//        Assertions.assertNull(expectedReview.getRatings().afterSalesRating());
//        Assertions.assertEquals(expectedReview.getStatus(), Status.PENDING);
//        Assertions.assertEquals(expectedReview.getWeakPoints().getValue().size(), 0);
//        Assertions.assertEquals(expectedReview.getStrongPoints().getValue().size(), 0);
//        Assertions.assertEquals(expectedReview.getType(), expectedType);
//        Assertions.assertEquals(expectedReview.getBoughtFrom(), expectedSource);
//        Assertions.assertNull(expectedReview.getRecommends());
//        Assertions.assertNull(expectedReview.getUrl());
    }

    @Test
    public void givenAllValuesReview_whenInstantiate_shouldCreateANewReview() {

//        final var expectedReviewId = ReviewId.unique();
//        final var expectedUserId = UserId.unique();
//        final var expectedTitle = "some description";
//        final var expectedComment = "some comment";
//        final var expectedRating = RatingScale.RA_5;
//        final var expectedWeakPoints = Set.of("note1", "note2");
//        final var expectedStrongPoints = Set.of("note1", "note2");
//        final var expectedNow = InstantUtils.now();
//        final var expectedType = Type.PRODUCT;
//        final var expectedSource = "any";
//        final var expectedUrl = "https://www.google.com.br";
//
//        final var expectedReview = new ReviewBuilder(expectedTitle,
//                expectedComment,
//                expectedUserId,
//                expectedType,
//                expectedSource,
//                expectedRating
//        )
//                .withReviewId(expectedReviewId.getValue())
//                .withActive(true)
//                .withWeakPoints(expectedWeakPoints)
//                .withStrongPoints(expectedStrongPoints)
//                .withStatus(Status.IN_ANALYSIS)
//                .withCreatedAt(expectedNow)
//                .withUpdatedAt(expectedNow)
//                .withDeletedAt(expectedNow)
//                .withReclameAquiUrl(expectedUrl)
//                .withIsVerified(true)
//                .withIsRecommend(true)
//                .build();
//
//        Assertions.assertEquals(expectedReview.getId(), expectedReviewId);
//        Assertions.assertEquals(expectedReview.getUserId(), expectedUserId);
//        Assertions.assertEquals(expectedReview.getTitle(), expectedTitle);
//        Assertions.assertEquals(expectedReview.getSummary(), expectedComment);
//        Assertions.assertTrue(expectedReview.isActive());
//        Assertions.assertEquals(expectedReview.getCreatedAt(), expectedNow);
//        Assertions.assertEquals(expectedReview.getUpdatedAt(), expectedNow);
//        Assertions.assertEquals(expectedReview.getDeletedAt(), expectedNow);
//
//        Assertions.assertEquals(expectedReview.getRatings().overallRating(), expectedRating.getValue());
//        Assertions.assertNull(expectedReview.getRatings().supportRating());
//        Assertions.assertNull(expectedReview.getRatings().afterSalesRating());
//
//        Assertions.assertEquals(expectedReview.getStatus(), Status.IN_ANALYSIS);
//        Assertions.assertEquals(expectedReview.getWeakPoints().getValue(), expectedWeakPoints);
//        Assertions.assertEquals(expectedReview.getStrongPoints().getValue(), expectedWeakPoints);
//        Assertions.assertEquals(expectedReview.getUrl().getValue(), expectedUrl);
//        Assertions.assertTrue(expectedReview.getRecommends());
//        Assertions.assertTrue(expectedReview.isVerified());

    }
}
