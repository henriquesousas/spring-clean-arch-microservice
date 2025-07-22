package br.com.opinai.api.conta.domain;

import com.opinai.shared.domain.utils.EnumUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class RoleTest {


    @Test
    public void givenAValidRoles_whenCreate_shouldReturnCorrectValues() {
        final var expectedRoles = Set.of(Role.USER);

        final var role = expectedRoles
                .stream()
                .map(value -> EnumUtils.of(Role.values(), value.getValue()).getValue())
                .toList()
                .get(0);

        Assertions.assertEquals("USER", role);
    }
}
