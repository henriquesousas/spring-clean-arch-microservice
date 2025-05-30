package com.jobee.admin.service.domain.user;

import com.jobee.admin.service.domain.shared.validation.Error;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

public class UserIdTest {

    @Test
    public void giveAValidUserId_whenInstantiated_thenItShouldBeValid() {
        // given
        final var expectedUserId = "123e4567e89b12d3a456426614174000";

        // when
        final var userId = UserId.from(expectedUserId);

        // then
        Assertions.assertEquals(expectedUserId, userId.getValue());
        Assertions.assertFalse(userId.getNotification().hasError());
    }

    @Test
    public void giveAInValidUUID_whenInstantiated_thenItShouldBeInvalid() {
        // given
        final var expectedUserId = "123e4567-e89b";
        final var expectedError = "UserId must be a valid UUID";

        // when
        final var userId = UserId.from(expectedUserId);

        // then
        Assertions.assertTrue(userId.getNotification().hasError());
        Assertions.assertEquals(expectedError, userId.getNotification().getFirstError().message());
        Assertions.assertEquals(expectedUserId, userId.getValue());
    }

    @Test
    public void giveAnEmptyUUID_whenInstantiated_thenItShouldBeInvalid() {
        // given
        final var expectedUserId = "";
        final var expectedError1 = new Error("UserId cannot be null or empty");
        final var expectedError2 = new Error("UserId must be a valid UUID");

        // when
        final var userId = UserId.from(expectedUserId);

        // then
        Assertions.assertTrue(userId.getNotification().hasError());
        Assertions.assertEquals(
                new HashSet<>(userId.getNotification().getErrors()),
                new HashSet<>(List.of(expectedError1, expectedError2)));
        Assertions.assertEquals(expectedUserId, userId.getValue());
    }
}
