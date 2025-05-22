package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.Rating;
import com.jobee.admin.service.domain.review.enums.ReviewStatus;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.review.valueobjects.ReviewPoint;
import com.jobee.admin.service.domain.shared.utils.InstantUtils;
import com.jobee.admin.service.domain.shared.validation.Error;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        Assertions.assertEquals(expectedReview.getPositivePoints().size(), 0);
        Assertions.assertEquals(expectedReview.getNegativePoints().size(), 0);
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
                .withPositivePoints(expectedPros)
                .withNegativePoints(expectedCons)
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
        Assertions.assertEquals(expectedReview.getPositivePoints(), expectedPros);
        Assertions.assertEquals(expectedReview.getNegativePoints(), expectedCons);

    }

    @Test
    public void giveAnActiveReview_whenDeactivate_shouldUpdateIsActiveToFalse() {

        final var expectedReview = new ReviewBuilder("some description", "some comment", UserId.unique())
                .withActive(true)
                .build();

        expectedReview.deactivate();
        Assertions.assertFalse(expectedReview.isActive());
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNotNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnInActiveReview_whenActivate_shouldUpdateIsActiveToTrue() {

        final var expectedReview = new ReviewBuilder("some description", "some comment", UserId.unique())
                .build();

        expectedReview.activate();

        Assertions.assertTrue(expectedReview.isActive());
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnActiveReview_whenChangeComment_shouldUpdate() {

        final var expectedActualComment = "some comment";
        final var expectedUpdatedComment = "some comment updated";
        final var expectedReview = new ReviewBuilder("some description", expectedActualComment, UserId.unique())
                .withActive(true)
                .build();

        Assertions.assertEquals(expectedReview.getComment(), expectedActualComment);

        expectedReview.changeComment(expectedUpdatedComment);

        Assertions.assertEquals(expectedReview.getComment(), expectedUpdatedComment);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnInActiveReview_whenChangeComment_shouldNotUpdate() {

        final var expectedActualComment = "some comment updated";
        final var expectedError = new Error("Os comentários não podem ser alterados para avaliações inativas");
        final var expectedReview = new ReviewBuilder("some description", expectedActualComment, UserId.unique())
                .build();

        expectedReview.changeComment(expectedActualComment);

        Assertions.assertEquals(expectedReview.getComment(), expectedActualComment);
        Assertions.assertNull(expectedReview.getDeletedAt());
        Assertions.assertTrue(expectedReview.getNotification().hasError());
        Assertions.assertEquals(expectedReview.getNotification().getFirstError().message(), expectedError.message());
    }

    @Test
    public void giveAnActiveReview_whenChangeTitle_shouldUpdate() {

        final var expectedActualTitle = "some title";
        final var expectedUpdatedTitle = "some title updated";
        final var expectedReview = new ReviewBuilder("some title", expectedActualTitle, UserId.unique())
                .withActive(true)
                .build();

        Assertions.assertEquals(expectedReview.getTitle(), expectedActualTitle);

        expectedReview.changeTitle(expectedUpdatedTitle);

        Assertions.assertEquals(expectedReview.getTitle(), expectedUpdatedTitle);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnActiveReview_whenChangeStatus_shouldUpdate() {
        final var expectedReview = new ReviewBuilder("some title", "any", UserId.unique())
                .withActive(true)
                .build();

        expectedReview.changeStatus(ReviewStatus.APPROVED);

        Assertions.assertEquals(expectedReview.getStatus(), ReviewStatus.APPROVED);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnActiveReview_whenChangeRating_shouldUpdate() {
        final var expectedReview = new ReviewBuilder("some title", "any", UserId.unique())
                .withActive(true)
                .build();

        Assertions.assertNull(expectedReview.getRating());

        expectedReview.changeRating(Rating.RA_1);

        Assertions.assertEquals(expectedReview.getRating(), Rating.RA_1);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnActiveReview_whenAddPositivePoints_shouldUpdate() {
        final var expectedPoints = Set.of(ReviewPoint.of("pos 1"), ReviewPoint.of("pos 2"));

        final var expectedReview = new ReviewBuilder("some title", "any", UserId.unique())
                .withActive(true)
                .build();

        expectedPoints.forEach(expectedReview::addPositivePoints);

        Assertions.assertEquals(expectedReview.getPositivePoints(), expectedPoints);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnActiveReview_whenRemovePositivePoints_shouldUpdate() {
        final var expectedDefaultPoints = Set.of(ReviewPoint.of("pos 1"), ReviewPoint.of("pos 2"));
        final var expectedActualPoints = Set.of(ReviewPoint.of("pos 2"));

        final var expectedReview = new ReviewBuilder("some title", "any", UserId.unique())
                .withActive(true)
                .withPositivePoints(expectedDefaultPoints)
                .build();

        Assertions.assertEquals(expectedReview.getPositivePoints(), expectedDefaultPoints);

        expectedReview.removePositivePoint(ReviewPoint.of("pos 1"));

        Assertions.assertEquals(expectedReview.getPositivePoints(), expectedActualPoints);

        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnActiveReview_whenAddNegativePoints_shouldUpdate() {
        final var expectedPoints = Set.of(ReviewPoint.of("pos 1"), ReviewPoint.of("pos 2"));

        final var expectedReview = new ReviewBuilder("some title", "any", UserId.unique())
                .withActive(true)
                .build();

        expectedPoints.forEach(expectedReview::addNegativePoint);

        Assertions.assertEquals(expectedReview.getNegativePoints(), expectedPoints);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnActiveReview_whenRemoveNegativePoints_shouldUpdate() {
        final var expectedDefaultPoints = Set.of(ReviewPoint.of("pos 1"), ReviewPoint.of("pos 2"));
        final var expectedActualPoints = Set.of(ReviewPoint.of("pos 2"));

        final var expectedReview = new ReviewBuilder("some title", "any", UserId.unique())
                .withActive(true)
                .withNegativePoints(expectedDefaultPoints)
                .build();

        Assertions.assertEquals(expectedReview.getNegativePoints(), expectedDefaultPoints);

        expectedReview.removeNegativePoint(ReviewPoint.of("pos 1"));

        Assertions.assertFalse(expectedReview.getNotification().hasError());
        Assertions.assertEquals(expectedReview.getNegativePoints(), expectedActualPoints);
        Assertions.assertTrue(expectedReview.getCreatedAt().isBefore(expectedReview.getUpdatedAt()));
        Assertions.assertNull(expectedReview.getDeletedAt());
    }

    @Test
    public void giveAnInvalidReview_whenCallsValidate_shouldNotificaiton() {
        final var expectedPositivePoints = Set.of(ReviewPoint.of("11111111111111111111111111111111111"));
        final var expectedPositivePointsError = List.of(
                new Error("Review não pode exceder 30 caracteres"),
                new Error("'titúlo' não deve ser nulo ou vazio"),
                new Error("'titúlo' deve ter entre 3 e 255 caracteres"),
                new Error("'comentario' não deve ser nulo ou vazio"),
                new Error("'comentario' deve ter entre 3 e 255 caracteres")
        );

        final var expectedReview = new ReviewBuilder("", "", UserId.unique())
                .withActive(true)
                .withPositivePoints(expectedPositivePoints)
                .build();

        Assertions.assertTrue(expectedReview.getNotification().hasError());
        Assertions.assertEquals(
                new HashSet<>(expectedReview.getNotification().getErrors()) ,
                new HashSet<>(expectedPositivePointsError));
    }
}
