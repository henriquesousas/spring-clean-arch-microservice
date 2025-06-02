package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

public class ReviewValidatorTest {

    @Test
    public void giveAnInvalidReview_whenCallsValidate_shouldHaveNotificationError() {
        final var expectedType = Type.PRODUCT;
        final var expectedSource = "any";
        final var expectedWeakPoints = new HashSet<String>();
        final var expectedStrongPoints = new HashSet<String>();
        for (int i = 0; i<31; i++) {
            expectedWeakPoints.add("note" + i);
            expectedStrongPoints.add("note" + i);
        }

        final var expectedErrors = List.of(
                new Error("'titúlo' não deve ser nulo ou vazio"),
                new Error("'titúlo' deve ter entre 3 e 255 caracteres"),
                new Error("'comentario' não deve ser nulo ou vazio"),
                new Error("'comentario' deve ter entre 3 e 255 caracteres"),
                new Error("Pontos fraco execedeu o limite"),
                new Error("Pontos forte execedeu o limite")
        );

        final var expectedReview = new ReviewBuilder(
                "",
                "",
                UserId.unique(),
                expectedType,
                expectedSource,
                RatingScale.RA_1,
                RatingScale.RA_1,
                RatingScale.RA_1
        )
                .withActive(true)
                .withWeakPoints(expectedWeakPoints)
                .withStrongPoints(expectedStrongPoints)
                .build();

        expectedReview.getNotification().getErrors().forEach(error -> System.out.println(error.message()));

        Assertions.assertTrue(expectedReview.getNotification().hasError());
        Assertions.assertEquals(
                new HashSet<>(expectedErrors),
                new HashSet<>(expectedReview.getNotification().getErrors())
        );
    }
}
