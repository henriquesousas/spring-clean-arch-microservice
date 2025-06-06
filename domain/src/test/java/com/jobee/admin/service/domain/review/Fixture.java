package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.core.review.ReviewBuilder;
import com.jobee.admin.service.domain.core.review.enums.Status;
import com.jobee.admin.service.domain.core.review.enums.Type;
import com.jobee.admin.service.domain.core.review.enums.RatingScale;
import com.jobee.admin.service.domain.core.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.core.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.core.review.valueobjects.Url;
import com.jobee.admin.service.domain.core.user.valueobjects.UserId;
import com.jobee.admin.service.domain.utils.InstantUtils;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class Fixture {

    public static final UserId expectedUserId = UserId.unique();
    public static final ReviewId expectedReviewId = ReviewId.unique();
    public static final String expectedTitle = "Produto excelente";
    public static final String expectedSummary = "Entrega rápida, recomendo";
    public static final Type expectedType = Type.PRODUCT;
    public static final String expectedBoughtFrom = "Loja XYZ";
    public static final RatingScale expectedOverall = RatingScale.RA_1;
    public static final RatingScale expectedPostSale = RatingScale.RA_1;
    public static final RatingScale expectedResponseTime = RatingScale.RA_1;
    public static Set<Feedback> expectedPositiveFeedback = Set.of(Feedback.from("Muito bom"));
    public static Set<Feedback> expectedNegativeFeedback = Set.of(Feedback.from("Muito pequeno"));
    public static Instant expectedCreatedAt = InstantUtils.now();
    public static Instant expectedUpdateAt = InstantUtils.now();
    public static Instant expectedDeletedAt = InstantUtils.now();
    public static Url expectedUrl = Url.from( "http://googgle.com.br");
    public static boolean expectedRecommend = true;
    public static Status expectedStatus = Status.IN_ANALYSIS;
    public static boolean expectedIsActive = true;


    public static ReviewBuilder reviewWithDefaultValues() {
        return new ReviewBuilder(
                expectedTitle,
                expectedSummary,
                expectedUserId,
                expectedType,
                expectedBoughtFrom,
                null,
                expectedOverall,
                null,
                null,
                null,
                null
        );
    }

    public static ReviewBuilder reviewWithAllValues() {
        return new ReviewBuilder(
                expectedTitle,
                expectedSummary,
                expectedUserId,
                expectedType,
                expectedBoughtFrom,
                expectedUrl,
                expectedOverall,
                expectedPostSale,
                expectedResponseTime,
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
                .withIsVerified(true);

    }

    public static ReviewBuilder reviewWithInvalidValues() {

        final var expectedPositiveFeedback = new HashSet<Feedback>();
        final var expectedNegativeFeedback = new HashSet<Feedback>();
        for (int i = 0; i < 32; i++) {
            expectedPositiveFeedback.add(Feedback.from("note" + i));
            expectedNegativeFeedback.add(Feedback.from("note" + i));
        }
        return new ReviewBuilder(
                "",
                "",
                expectedUserId,
                expectedType,
                expectedBoughtFrom,
                null,
                RatingScale.RA_1,
                RatingScale.RA_1,
                RatingScale.RA_1,
                expectedPositiveFeedback,
                expectedNegativeFeedback
        )
                .withActive(true);
    }
}
