package br.com.opinai.api.conta.domain;

import br.com.opinai.api.conta.domain.valueobjects.BirthDate;
import br.com.opinai.api.conta.domain.valueobjects.Email;
import br.com.opinai.api.conta.domain.valueobjects.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class AddPhotoUrlTest extends UnitTest {

    @Test
    public void givenAnUser_whenAddPhoto_thenSuccess() {
        // Arrange
        final var expectedName = "John";
        final var expectedLastName = "Doe";
        final var expectedEmail = "u1@gmail.com";
        final var expectedPassword = "password123";
        final var expectedPhone = "11972732246";
        final var expectedRoles = Set.of(Role.ADMIN);
        final var expectedBirthDate = BirthDate.from("27/08/1986");

        final var expectedUser = UserBuilder.create(
                expectedName,
                expectedLastName,
                expectedEmail,
                Gender.MALE,
                expectedBirthDate,
                expectedPassword,
                expectedPhone,
                expectedRoles
        ).build();

        final var expectedPhotoUrl = "https://fake.jpg";
        expectedUser.addPhoto(expectedPhotoUrl);

        Assertions.assertEquals(expectedPhotoUrl , expectedUser.getPhotoUrl().getValue());
        Assertions.assertFalse(expectedUser.getNotification().hasError());

    }
}
