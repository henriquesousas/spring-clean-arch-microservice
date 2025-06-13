package com.jobee.admin.service.domain.user;

import com.jobee.admin.service.domain.commons.utils.InstantUtils;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class UserTest {

    @Test
    public void givenDefaultValidUser_whenUserIsCreated_thenUserShouldBeCreated() {
        // Arrange
        final var expectedName = "John Doe";
        final var expectedEmail = "u1@gmail.com";
        final var expectedPassword = "password123";
        final var expectedPhone = "1234567890";
        final var expectedRoles = Set.of("USER", "ADMIN");
        final var expectedUser = new UserBuilder(
                expectedName,
                expectedEmail,
                expectedPassword,
                expectedPhone,
                expectedRoles
        ).build();

        // Act
        Assertions.assertFalse(expectedUser.getNotification().hasError());
        Assertions.assertEquals(expectedUser.getName(), expectedName);
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
        final var expectedEmail = "u1@gmail.com";
        final var expectedPassword = "password123";
        final var expectedPhone = "1234567890";
        final var expectedRoles = Set.of("USER");
        final var expectedDate = InstantUtils.now();
        final var expectedUser = new UserBuilder(
                expectedName,
                expectedEmail,
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
        Assertions.assertEquals(expectedUser.getName(), expectedName);
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
