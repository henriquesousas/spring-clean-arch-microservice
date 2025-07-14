package com.opinai.account.domain;

import com.opinai.account.domain.valueobjects.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailTest  extends UnitTest {

    @Test
    public void givenAValidEmail_whenInstantiated_thenItShouldBeValid() {
        // given
        final var expectedEmail = "email@gmail.com";
        // when
        final var email = Email.from(expectedEmail);
        // then
        Assertions.assertFalse(email.getNotification().hasError());
        //TODO: here
//        Assertions.assertNull( email.getNotification().getFirstError());
    }

    @Test
    public void givenAnInValidEmail_whenInstantiated_thenItShouldInvalid() {
        // given
        final var expectedEmail = "emailgmail.com";
        final var expectedErrorMessage = "Email inválido";
        // when
        final var email = Email.from(expectedEmail);
        // then
        Assertions.assertTrue(email.getNotification().hasError());
        //TODO: here
//        Assertions.assertEquals(expectedErrorMessage  ,email.getNotification().getFirstError().message());
    }
}
