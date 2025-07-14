package com.opinai.account.domain;

import com.opinai.account.domain.valueobjects.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PhoneTest  extends UnitTest {

    @Test
    public void givenAValidPhoneNumber_whenInstantiated_thenItShouldBeValid() {
        // given
        final var expectedPassword = "11972742245";
        // when
        final var value = Phone.from(expectedPassword);
        // then
        Assertions.assertFalse(value.getNotification().hasError());
        //TODO: here
//        Assertions.assertNull( value.getNotification().getFirstError());
    }

    @Test
    public void givenAnInValidPassword_whenInstantiated_thenItShouldInvalid() {
        // given
        final var expectedPassword = "12345";
        final var expectedErrorMessage = "Número de telefone inválido";
        // when
        final var value = Phone.from(expectedPassword);
        // then
        Assertions.assertTrue(value.getNotification().hasError());
        //TODO: here
//        Assertions.assertEquals(expectedErrorMessage  ,value.getNotification().getFirstError().message());

    }
}
