package com.jobee.admin.service.domain.review;

import org.junit.jupiter.api.Test;

public class ReviewCreationTest {

    @Test
    public void givenADefaultValue_whenInstantiate_shouldCreateANewReview() {

//        final var expectedUserId = UserId.unique();
//        final var expectedTitle = "some description";
//        final var expectedComment = "some comment";
//        final var expectedType = ProductType.PRODUCT;
//        final var expectedSource = "any";
//
//        final var expectedReview = new ReviewBuilder(
//                expectedTitle,
//                expectedComment,
//                expectedUserId,
//                expectedType,
//                expectedSource,
//                Ratings.newRating(RatingOptions.RA_1)
//        ).build();
//
//        Assertions.assertEquals(expectedReview.getUserId(), expectedUserId);
//        Assertions.assertEquals(expectedReview.getTitle(), expectedTitle);
//        Assertions.assertEquals(expectedReview.getSummary(), expectedComment);
//        Assertions.assertFalse(expectedReview.isActive());
//        Assertions.assertNotNull(expectedReview.getCreatedAt());
//        Assertions.assertNotNull(expectedReview.getUpdatedAt());
//        Assertions.assertNull(expectedReview.getDeletedAt());
//
//        Assertions.assertEquals(expectedReview.getRating().getOverallRating(), RatingOptions.RA_1);
//        Assertions.assertNull(expectedReview.getRating().getSupportRating());
//        Assertions.assertNull(expectedReview.getRating().getAfterSallestRating());
//
//        Assertions.assertEquals(expectedReview.getStatus(), Status.PENDING);
//        Assertions.assertEquals(expectedReview.getNotes().getPositives().size(), 0);
//        Assertions.assertEquals(expectedReview.getNotes().getNegatives().size(), 0);
//
//        Assertions.assertEquals(expectedReview.getProductType(), expectedType);
//        Assertions.assertEquals(expectedReview.getPurchaseSource(), expectedSource);
//        Assertions.assertNull(expectedReview.getRecommends());
//        Assertions.assertNull(expectedReview.getUrlReclameAqui());
    }

    @Test
    public void givenAllValuesReview_whenInstantiate_shouldCreateANewReview() {

//        final var expectedReviewId = ReviewId.unique();
//        final var expectedUserId = UserId.unique();
//        final var expectedTitle = "some description";
//        final var expectedComment = "some comment";
//        final var expectedRating = RatingOptions.RA_5;
//        final var expectedPositive = Set.of("note1", "note2");
//        final var expectedNegative = Set.of("note1", "note2");
//        final var expectedNow = InstantUtils.now();
//        final var expectedType = ProductType.PRODUCT;
//        final var expectedSource = "any";
//        final var expectedUrl = "https://www.google.com.br";
//
//        final var expectedReview = new ReviewBuilder(expectedTitle,
//                expectedComment,
//                expectedUserId,
//                expectedType,
//                expectedSource,
//                Ratings.newRating(expectedRating)
//        )
//                .withId(expectedReviewId)
//                .withActive(true)
//                .withNotes(expectedPositive, expectedNegative)
//                .withStatus(Status.IN_ANALYSIS)
//                .withCreatedAt(expectedNow)
//                .withUpdatedAt(expectedNow)
//                .withDeletedAt(expectedNow)
//                .withVerifiedAt(expectedNow)
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
//        Assertions.assertEquals(expectedReview.getRating().getOverallRating(), expectedRating);
//        Assertions.assertNull(expectedReview.getRating().getSupportRating());
//        Assertions.assertNull(expectedReview.getRating().getAfterSallestRating());
//
//        Assertions.assertEquals(expectedReview.getStatus(), Status.IN_ANALYSIS);
//        Assertions.assertEquals(expectedReview.getNotes().getPositives(), expectedPositive);
//        Assertions.assertEquals(expectedReview.getNotes().getNegatives(), expectedPositive);
//        Assertions.assertEquals(expectedReview.getUrlReclameAqui().getValue(), expectedUrl);
//        Assertions.assertTrue(expectedReview.getRecommends());
//        Assertions.assertTrue(expectedReview.isVerified());

    }
}
