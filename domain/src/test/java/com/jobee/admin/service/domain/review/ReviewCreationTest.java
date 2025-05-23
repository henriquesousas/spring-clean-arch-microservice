package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.review.enums.ReviewStatus;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.review.valueobjects.ReviewPoint;
import com.jobee.admin.service.domain.shared.utils.InstantUtils;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ReviewCreationTest {

    @Test
    public void givenADefaultValue_whenInstantiate_shouldCreateANewReview() {

        final var expectedUserId = UserId.unique();
        final var expectedTitle = "some description";
        final var expectedComment = "some comment";
        final var expectedType = ProductType.PRODUCT;
        final var expectedSource = "any";

        final var expectedReview = new ReviewBuilder(
                expectedTitle,
                expectedComment,
                expectedUserId,
                expectedType,
                expectedSource,
                Rating.newRating(RatingOptions.RA_1)
        ).build();

        Assertions.assertEquals(expectedReview.getUserId(), expectedUserId);
        Assertions.assertEquals(expectedReview.getTitle(), expectedTitle);
        Assertions.assertEquals(expectedReview.getSummary(), expectedComment);
        Assertions.assertFalse(expectedReview.isActive());
        Assertions.assertNotNull(expectedReview.getCreatedAt());
        Assertions.assertNotNull(expectedReview.getUpdatedAt());
        Assertions.assertNull(expectedReview.getDeletedAt());

        Assertions.assertEquals(expectedReview.getRating().getOverallRating(), RatingOptions.RA_1);
        Assertions.assertNull(expectedReview.getRating().getSupportResponseTimeRating());
        Assertions.assertNull(expectedReview.getRating().getAfterSalesServiceRating());

        Assertions.assertEquals(expectedReview.getStatus(), ReviewStatus.PENDING);
        Assertions.assertEquals(expectedReview.getPositivePoints().size(), 0);
        Assertions.assertEquals(expectedReview.getNegativePoints().size(), 0);

        Assertions.assertEquals(expectedReview.getProductType(), expectedType);
        Assertions.assertEquals(expectedReview.getPurchaseSource(), expectedSource);
        Assertions.assertNull(expectedReview.getRecommends());
        Assertions.assertNull(expectedReview.getReclameAquiUrl());
    }

    @Test
    public void givenAllValuesReview_whenInstantiate_shouldCreateANewReview() {

        final var expectedReviewId = ReviewId.unique();
        final var expectedUserId = UserId.unique();
        final var expectedTitle = "some description";
        final var expectedComment = "some comment";
        final var expectedRating = RatingOptions.RA_5;
        final var expectedPros = Set.of(ReviewPoint.of("pros 1"), ReviewPoint.of("pros 2"));
        final var expectedCons = Set.of(ReviewPoint.of("cons 1"));
        final var expectedNow = InstantUtils.now();
        final var expectedType = ProductType.PRODUCT;
        final var expectedSource = "any";
        final var expectedUrl = "https://www.google.com.br";

        final var expectedReview = new ReviewBuilder(expectedTitle,
                expectedComment,
                expectedUserId,
                expectedType,
                expectedSource,
                Rating.newRating(expectedRating)
        )
                .withId(expectedReviewId)
                .withActive(true)
                .withPositivePoints(expectedPros)
                .withNegativePoints(expectedCons)
                .withStatus(ReviewStatus.IN_ANALYSIS)
                .withCreatedAt(expectedNow)
                .withUpdatedAt(expectedNow)
                .withDeletedAt(expectedNow)
                .withVerifiedAt(expectedNow)
                .withReclameAquiUrl(expectedUrl)
                .withIsVerified(true)
                .withIsRecommend(true)
                .build();

        Assertions.assertEquals(expectedReview.getId(), expectedReviewId);
        Assertions.assertEquals(expectedReview.getUserId(), expectedUserId);
        Assertions.assertEquals(expectedReview.getTitle(), expectedTitle);
        Assertions.assertEquals(expectedReview.getSummary(), expectedComment);
        Assertions.assertTrue(expectedReview.isActive());
        Assertions.assertEquals(expectedReview.getCreatedAt(), expectedNow);
        Assertions.assertEquals(expectedReview.getUpdatedAt(), expectedNow);
        Assertions.assertEquals(expectedReview.getDeletedAt(), expectedNow);

        Assertions.assertEquals(expectedReview.getRating().getOverallRating(), expectedRating);
        Assertions.assertNull(expectedReview.getRating().getSupportResponseTimeRating());
        Assertions.assertNull(expectedReview.getRating().getAfterSalesServiceRating());

        Assertions.assertEquals(expectedReview.getStatus(), ReviewStatus.IN_ANALYSIS);
        Assertions.assertEquals(expectedReview.getPositivePoints(), expectedPros);
        Assertions.assertEquals(expectedReview.getNegativePoints(), expectedCons);
        Assertions.assertEquals(expectedReview.getReclameAquiUrl().getValue(), expectedUrl);
        Assertions.assertTrue(expectedReview.getRecommends());
        Assertions.assertTrue(expectedReview.isVerified());

    }
}
