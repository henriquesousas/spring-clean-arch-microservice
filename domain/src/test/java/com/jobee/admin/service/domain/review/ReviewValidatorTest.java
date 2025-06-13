package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.commons.validation.Error;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

public class ReviewValidatorTest {

    @Test
    public void giveAnInvalidReview_whenCallsValidate_shouldHaveNotificationError() {

        final var expectedErrors = List.of(
                new Error("'titúlo' não deve ser nulo ou vazio"),
                new Error("'titúlo' deve ter entre 3 e 255 caracteres"),
                new Error("'comentario' não deve ser nulo ou vazio"),
                new Error("'comentario' deve ter entre 3 e 255 caracteres"),
                new Error("Feedback positivo excedeu o limite permitido"),
                new Error("Feedback negativo excedeu o limite permitido")
        );


        final var expectedReview = Fixture.reviewWithInvalidValues().build();

        expectedReview.getNotification().getErrors().forEach(error -> System.out.println(error.message()));

        Assertions.assertTrue(expectedReview.getNotification().hasError());
        Assertions.assertEquals(
                new HashSet<>(expectedErrors),
                new HashSet<>(expectedReview.getNotification().getErrors())
        );
    }
}
