package com.jobee.admin.service.domain.review;

import org.junit.jupiter.api.Test;

public class ReviewValidatorTest {

    @Test
    public void giveAnInvalidReview_whenCallsValidate_shouldHaveNotificationError() {
//        final var expectedType = ProductType.PRODUCT;
//        final var expectedSource = "any";
//        final var expectedPositiveNotes = new HashSet<String>();
//        final var expectedNegativeNotes = new HashSet<String>();
//        for (int i = 0; i<31; i++) {
//            expectedPositiveNotes.add("note" + i);
//            expectedNegativeNotes.add("note" + i);
//        }
//
//        final var expectedErrors = List.of(
//                new Error("'titúlo' não deve ser nulo ou vazio"),
//                new Error("'titúlo' deve ter entre 3 e 255 caracteres"),
//                new Error("'comentario' não deve ser nulo ou vazio"),
//                new Error("'comentario' deve ter entre 3 e 255 caracteres"),
//                new Error("Nota positiva excede o tamanho máximo de 30 caracteres"),
//                new Error("Nota negativa excede o tamanho máximo de 30 caracteres")
//        );
//
//        final var expectedReview = new ReviewBuilder(
//                "",
//                "",
//                UserId.unique(),
//                expectedType,
//                expectedSource,
//                Ratings.newRating(RatingOptions.RA_1)
//        )
//                .withActive(true)
//                .withNotes(expectedPositiveNotes, expectedNegativeNotes)
//                .build();
//
//        Assertions.assertTrue(expectedReview.getNotification().hasError());
//        Assertions.assertEquals(
//                new HashSet<>(expectedErrors),
//                new HashSet<>(expectedReview.getNotification().getErrors())
//        );
    }
}
