package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.shared.validation.Error;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReviewValidatorTest {

    @Test
    public void giveAnInvalidReview_whenCallsValidate_shouldHaveNotificationError() {
        final var expectedType = ProductType.PRODUCT;
        final var expectedSource = "any";
        final var expectedPositivePoints = Set.of(Feedback.of("11111111111111111111111111111111111"));
        final var expectedPositivePointsError = List.of(
                new Error("Review não pode exceder 30 caracteres"),
                new Error("'titúlo' não deve ser nulo ou vazio"),
                new Error("'titúlo' deve ter entre 3 e 255 caracteres"),
                new Error("'comentario' não deve ser nulo ou vazio"),
                new Error("'comentario' deve ter entre 3 e 255 caracteres")
        );

        final var expectedReview = new ReviewBuilder("", "", UserId.unique(), expectedType, expectedSource,Rating.newRating(RatingOptions.RA_1))
                .withActive(true)
                .withPositivePoints(expectedPositivePoints)
                .build();

        Assertions.assertTrue(expectedReview.getNotification().hasError());
        Assertions.assertEquals(
                new HashSet<>(expectedReview.getNotification().getErrors()),
                new HashSet<>(expectedPositivePointsError));
    }
}
