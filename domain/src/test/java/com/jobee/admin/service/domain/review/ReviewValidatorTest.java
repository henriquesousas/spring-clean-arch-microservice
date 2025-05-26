package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.review.valueobjects.Notes;
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
        final var expectedPositiveNotes = new HashSet<String>();
        final var expectedNegativeNotes = new HashSet<String>();
        for (int i = 0; i<31; i++) {
            expectedPositiveNotes.add("note" + i);
            expectedNegativeNotes.add("note" + i);
        }

        final var expectedErrors = List.of(
                new Error("'titúlo' não deve ser nulo ou vazio"),
                new Error("'titúlo' deve ter entre 3 e 255 caracteres"),
                new Error("'comentario' não deve ser nulo ou vazio"),
                new Error("'comentario' deve ter entre 3 e 255 caracteres"),
                new Error("Nota positiva excede o tamanho máximo de 30 caracteres"),
                new Error("Nota negativa excede o tamanho máximo de 30 caracteres")
        );

        final var expectedReview = new ReviewBuilder(
                "",
                "",
                UserId.unique(),
                expectedType,
                expectedSource,
                Rating.newRating(RatingOptions.RA_1)
        )
                .withActive(true)
                .withNotes(expectedPositiveNotes, expectedNegativeNotes)
                .build();

        Assertions.assertTrue(expectedReview.getNotification().hasError());
        Assertions.assertEquals(
                new HashSet<>(expectedErrors),
                new HashSet<>(expectedReview.getNotification().getErrors())
        );
    }
}
