package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.Rating;
import com.jobee.admin.service.domain.review.enums.ReviewStatus;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.review.valueobjects.ReviewPoint;
import com.jobee.admin.service.domain.shared.utils.InstantUtils;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;


public class ReviewTest {

    @Test
    public void givenADefaultValue_whenInstantiate_shouldCreateANewReview() {

        final var expectedUserId = UserId.unique();
        final var expectedTitle = "some description";
        final var expectedComment = "some comment";

        final var expectedReview = new ReviewBuilder(expectedTitle, expectedComment, expectedUserId)
                .build();

        Assertions.assertEquals(expectedReview.getUserId(), expectedUserId);
        Assertions.assertEquals(expectedReview.getTitle(), expectedTitle);
        Assertions.assertEquals(expectedReview.getComment(), expectedComment);
        Assertions.assertFalse(expectedReview.isActive());
        Assertions.assertNotNull(expectedReview.getCreatedAt());
        Assertions.assertNotNull(expectedReview.getUpdatedAt());
        Assertions.assertNull(expectedReview.getDeletedAt());
        Assertions.assertNull(expectedReview.getRating());
        Assertions.assertEquals(expectedReview.getStatus(), ReviewStatus.PENDING);
        Assertions.assertEquals(expectedReview.getPros().size(), 0);
        Assertions.assertEquals(expectedReview.getCons().size(), 0);
    }


    @Test
    public void givenAllValuesReview_whenInstantiate_shouldCreateANewReview() {

        final var expectedReviewId = ReviewId.unique();
        final var expectedUserId = UserId.unique();
        final var expectedTitle = "some description";
        final var expectedComment = "some comment";
        final var expectedRating = Rating.RA_5;
        final var expectedRatingLiteralValue = expectedRating.getValue();
        final var expectedPros = Set.of(ReviewPoint.of("pros 1"), ReviewPoint.of("pros 2"));
        final var expectedCons = Set.of(ReviewPoint.of("cons 1"));
        final var expectedNow = InstantUtils.now();

        final var expectedReview = new ReviewBuilder(expectedTitle, expectedComment, expectedUserId)
                .withId(expectedReviewId)
                .withActive(true)
                .withPros(expectedPros)
                .withCons(expectedCons)
                .withStatus(ReviewStatus.IN_ANALYSIS)
                .withRating(expectedRating)
                .withCreatedAt(expectedNow)
                .withUpdatedAt(expectedNow)
                .withDeletedAt(expectedNow)
                .withVerifiedAt(expectedNow)
                .build();

        Assertions.assertEquals(expectedReview.getId(), expectedReviewId);
        Assertions.assertEquals(expectedReview.getUserId(), expectedUserId);
        Assertions.assertEquals(expectedReview.getTitle(), expectedTitle);
        Assertions.assertEquals(expectedReview.getComment(), expectedComment);
        Assertions.assertTrue(expectedReview.isActive());
        Assertions.assertEquals(expectedReview.getCreatedAt(), expectedNow);
        Assertions.assertEquals(expectedReview.getUpdatedAt(), expectedNow);
        Assertions.assertEquals(expectedReview.getDeletedAt(), expectedNow);
        Assertions.assertEquals(expectedReview.getRating(), expectedRating);
        Assertions.assertEquals(expectedReview.getRating().getValue(), expectedRatingLiteralValue);
        Assertions.assertEquals(expectedReview.getStatus(), ReviewStatus.IN_ANALYSIS);
        Assertions.assertEquals(expectedReview.getPros(), expectedPros);
        Assertions.assertEquals(expectedReview.getCons(), expectedCons);

    }
}
