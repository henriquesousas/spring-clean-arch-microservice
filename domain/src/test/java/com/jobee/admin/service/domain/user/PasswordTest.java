package com.jobee.admin.service.domain.user;

import com.jobee.admin.service.domain.user.valueobjects.Password;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordTest {

    @Test
    public void givenAValidPassword_whenInstantiated_thenItShouldBeValid() {
        // given
        final var expectedPassword = "Password123!";
        // when
        final var value = Password.from(expectedPassword);
        // then
        Assertions.assertFalse(value.getNotification().hasError());
        Assertions.assertNull( value.getNotification().getFirstError());
    }

    @Test
    public void givenAnInValidPassword_whenInstantiated_thenItShouldInvalid() {
        // given
        final var expectedPassword = "123";
        final var expectedErrorMessage = "Password deve ter pelo menos 8 caracteres";
        // when
        final var value = Password.from(expectedPassword);
        // then
        Assertions.assertTrue(value.getNotification().hasError());
        Assertions.assertEquals(expectedErrorMessage  ,value.getNotification().getFirstError().message());
    }
}
