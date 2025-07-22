package br.com.opinai.api.conta.domain;

import br.com.opinai.api.conta.domain.valueobjects.BirthDate;
import com.opinai.shared.domain.utils.InstantUtils;
import com.opinai.shared.domain.validation.Error;
import com.opinai.shared.domain.validation.handler.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserValidatorTest {

    @Test
    public void givenAValidValues_whenCreateNewUser_shouldHaveNoValidationError() {
        final var expectedName = "Jhon";
        final var expectedLastName = "Jhon Doe";
        final var expectedEmail = "doe@gmail.com";
        final var expectedPassword = "12345678";
        final var expectedPhone = "11975455445";
        final var expectedRole = Set.of(Role.ADMIN);
        final var expectedGender = Gender.MALE;
        final var expectedBirthDate = BirthDate.from("27/08/1985");

        final var user = UserBuilder.create(
                expectedName,
                expectedLastName,
                expectedEmail,
                expectedGender,
                expectedBirthDate,
                expectedPassword,
                expectedPhone,
                expectedRole
        ).build();

        final var notification = Notification.create();

        final var sut = new UserValidator(user, notification);
        sut.validate();

        Assertions.assertFalse(notification.hasError());
    }

    @Test
    public void givenAInValidValues_whenCreateNewUser_shouldHaveNotifyError() {
        final var expectedName = "";
        final var expectedLastName = "";
        final var expectedEmail = "teste";
        final var expectedPassword = "1234567";
        final var expectedPhone = "11";
        final var expectedRole = Set.of(Role.ADMIN);
        final var expectedGender = Gender.MALE;
        final var expectedBirthDate = BirthDate.from(InstantUtils.toDateFormat());

        final var expectedErrors = List.of(
                new Error("'Nome' não deve ser nulo ou vazio"),
                new Error("'Nome' deve ter entre 3 e 255 caracteres"),
                new Error("'Último nome' não deve ser nulo ou vazio"),
                new Error("'Último nome' deve ter entre 3 e 255 caracteres"),
                new Error("Email inválido"),
                new Error("Só é possível se cadastrar a partir dos 16 anos"),
                new Error("Password deve ter pelo menos 8 caracteres"),
                new Error("Número de telefone inválido")
        );

        final var user = UserBuilder.create(
                expectedName,
                expectedLastName,
                expectedEmail,
                expectedGender,
                expectedBirthDate,
                expectedPassword,
                expectedPhone,
                expectedRole
        ).build();

        final var notification = Notification.create();

        final var sut = new UserValidator(user, notification);
        sut.validate();

        Assertions.assertTrue(notification.hasError());
        Assertions.assertEquals(
                new HashSet<>(expectedErrors),
                new HashSet<>(notification.getErrors())
        );
    }
}
