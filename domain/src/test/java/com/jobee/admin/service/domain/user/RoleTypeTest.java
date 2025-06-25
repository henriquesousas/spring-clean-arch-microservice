package com.jobee.admin.service.domain.user;

import com.jobee.admin.service.domain.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoleTypeTest  extends UnitTest {

    @Test
    public void givenAValidRole_whenInstantiated_thenItShouldBeValid() {
        // given
        final var expectedRole = "USER";
        // when
        final var value = RoleType.of(expectedRole).get();
        // then
        Assertions.assertNotNull(value);
        Assertions.assertEquals( expectedRole,value.name());
    }

    @Test
    public void givenAnInValidRole_whenInstantiated_thenItShouldBeInvalid() {
        // given
        final var expectedRole = "INVALID";
        final var expectedErrorMessage = "INVALID";
        // when
        final var value = RoleType.of(expectedRole);
        // then
        Assertions.assertTrue(value.isEmpty());
    }
}
