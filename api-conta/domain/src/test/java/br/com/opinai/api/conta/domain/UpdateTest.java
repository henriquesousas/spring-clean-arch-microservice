package br.com.opinai.api.conta.domain;

import br.com.opinai.api.conta.domain.valueobjects.BirthDate;
import br.com.opinai.api.conta.domain.valueobjects.Email;
import br.com.opinai.api.conta.domain.valueobjects.UnitTest;
import br.com.opinai.api.conta.domain.valueobjects.UserId;
import com.opinai.shared.domain.utils.InstantUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class UpdateTest extends UnitTest {

    @Test
    public void givenAnUser_whenUpdate_thenSuccess() {
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


        Assertions.assertFalse(expectedUser.getNotification().hasError());
        Assertions.assertEquals(expectedUser.getFirstName(), expectedName);
        Assertions.assertEquals(expectedUser.getLastName(), expectedLastName);
        Assertions.assertEquals(expectedUser.getEmail().getValue(), expectedEmail);
        Assertions.assertEquals(expectedUser.getPassword().getValue(), expectedPassword);
        Assertions.assertEquals(expectedUser.getPhone().getValue(), expectedPhone);
        Assertions.assertTrue(expectedUser.isActive());
        Assertions.assertEquals(expectedUser.getRoles().size(), expectedRoles.size());
        Assertions.assertNotNull(expectedUser.getCreatedAt());
        Assertions.assertNotNull(expectedUser.getUpdatedAt());
        Assertions.assertNull(expectedUser.getDeletedAt());

        final var expectedUpdatedUserName = "new name";
        final var expectedUpdatedLastName = "new last name";

        expectedUser.update(expectedUpdatedUserName, expectedUpdatedLastName, Email.from(expectedEmail), expectedBirthDate);

        Assertions.assertFalse(expectedUser.getNotification().hasError());
        Assertions.assertEquals(expectedUser.getFirstName(), expectedUpdatedUserName);
        Assertions.assertEquals(expectedUser.getLastName(), expectedUpdatedLastName);
        Assertions.assertEquals(expectedUser.getEmail().getValue(), expectedEmail);
        Assertions.assertNotNull(expectedUser.getCreatedAt());
        Assertions.assertTrue(expectedUser.getUpdatedAt().isAfter(expectedUser.getCreatedAt()));
        Assertions.assertNull(expectedUser.getDeletedAt());
    }
}
