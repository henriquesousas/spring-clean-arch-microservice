package br.com.opinai.api.conta.domain;

import br.com.opinai.api.conta.domain.valueobjects.BirthDate;
import br.com.opinai.api.conta.domain.valueobjects.UnitTest;
import br.com.opinai.api.conta.domain.valueobjects.UserId;
import com.opinai.shared.domain.utils.InstantUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class UserTest  extends UnitTest {

    @Test
    public void givenDefaultValidUser_whenUserIsCreated_thenUserShouldBeCreated() {
        // Arrange
        final var expectedName = "John";
        final var expectedLastName = "Doe";
        final var expectedEmail = "u1@gmail.com";
        final var expectedPassword = "password123";
        final var expectedPhone = "1234567890";
        final var expectedRoles = Set.of(Role.ADMIN);
        final var expectedUser =  UserBuilder.create(
                expectedName,
                expectedLastName,
                expectedEmail,
                Gender.MALE,
                BirthDate.from("27/08/1986"),
                expectedPassword,
                expectedPhone,
                expectedRoles
        ).build();

        // Act
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
    }


    @Test
    public void givenAllValidPropertyUser_whenUserIsCreated_thenUserShouldBeCreated() {
        // Arrange
        final var expecteUserId = UserId.unique();
        final var expectedName = "John Doe";
        final var expectedLastName = "Doe";
        final var expectedEmail = "u1@gmail.com";
        final var expectedPassword = "password123";
        final var expectedPhone = "1234567890";
        final var expectedRoles = Set.of(Role.USER);
        final var expectedDate = InstantUtils.now();
        final var expectedBirthDate =BirthDate.from("27/08/1985");

        final var expectedUser =  UserBuilder.create(
                expectedName,
                expectedLastName,
                expectedEmail,
                Gender.MALE,
                expectedBirthDate,
                expectedPassword,
                expectedPhone,
                expectedRoles
        ).withUserId(expecteUserId.getValue())
                .withIsActive(false)
                .withCreatedAt(expectedDate)
                .withUpdatedAt(expectedDate)
                .withDeletedAt(expectedDate)
                .build();

        // Act
        Assertions.assertFalse(expectedUser.getNotification().hasError());
        Assertions.assertEquals(expectedUser.getFirstName(), expectedName);
        Assertions.assertEquals(expectedUser.getEmail().getValue(), expectedEmail);
        Assertions.assertEquals(expectedUser.getPassword().getValue(), expectedPassword);
        Assertions.assertEquals(expectedUser.getPhone().getValue(), expectedPhone);
        Assertions.assertFalse(expectedUser.isActive());
        Assertions.assertEquals(expectedUser.getRoles().size(), expectedRoles.size());
        Assertions.assertEquals(expectedDate, expectedUser.getCreatedAt());
        Assertions.assertEquals(expectedDate,expectedUser.getUpdatedAt());
        Assertions.assertEquals(expectedDate,expectedUser.getDeletedAt());
    }
}
