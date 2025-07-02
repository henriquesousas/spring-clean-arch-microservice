package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.review.enums.Score;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.utils.IdUtils;
import com.jobee.admin.service.domain.utils.InstantUtils;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class Fixture {

    public static final String expectedUserId = IdUtils.uuid();
    public static final String expectedProductId = IdUtils.uuid();
    public static final ReviewId expectedReviewId = ReviewId.unique();
    public static final String expectedTitle = "Produto excelente";
    public static final String expectedComment = "Entrega rápida, recomendo";
    public static final Type expectedType = Type.PRODUCT;
    public static final String expectedStore = "Loja XYZ";
    public static final Score expectedRating = Score.ONE;

    public static Set<Feedback> expectedPositiveFeedback = Set.of(Feedback.from("Muito bom"));
    public static Set<Feedback> expectedNegativeFeedback = Set.of(Feedback.from("Muito pequeno"));
    public static Instant expectedCreatedAt = InstantUtils.now();
    public static Instant expectedUpdateAt = InstantUtils.now();
    public static Instant expectedDeletedAt = InstantUtils.now();
    public static String expectedUrl = "http://googgle.com.br";
    public static boolean expectedRecommend = true;
    public static Status expectedStatus = Status.IN_ANALYSIS;
    public static boolean expectedIsActive = true;

    public static ReviewBuilder reviewWithDefaultValues() {
        return ReviewBuilder.create(
                expectedUserId,
                expectedProductId,
                expectedRating,
                expectedTitle,
                expectedComment,
                null,
                null
        );
    }

    public static ReviewBuilder reviewWithAllValues() {
        return ReviewBuilder.create(
                        expectedUserId,
                        expectedProductId,
                        expectedRating,
                        expectedTitle,
                        expectedComment,
                        expectedPositiveFeedback,
                        expectedNegativeFeedback
                )
                .withReviewId(expectedReviewId.getValue())
                .withActive(expectedIsActive)
                .withStatus(expectedStatus)
                .withCreatedAt(expectedCreatedAt)
                .withUpdatedAt(expectedUpdateAt)
                .withDeletedAt(expectedDeletedAt)
                .withIsRecommend(expectedRecommend)
                .withUrl(expectedUrl)
                .withVerifiedPurchase(true);
    }

    public static ReviewBuilder reviewWithInvalidValues() {

        final var expectedPositiveFeedback = new HashSet<Feedback>();
        final var expectedNegativeFeedback = new HashSet<Feedback>();
        for (int i = 0; i < 32; i++) {
            expectedPositiveFeedback.add(Feedback.from("note" + i));
            expectedNegativeFeedback.add(Feedback.from("note" + i));
        }

        return ReviewBuilder.create(
                        "",
                        "",
                        expectedRating,
                        "",
                        "",
                        expectedPositiveFeedback,
                        expectedNegativeFeedback
                )
                .withActive(true);
    }
}
