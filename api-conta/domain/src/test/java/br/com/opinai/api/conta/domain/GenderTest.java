package br.com.opinai.api.conta.domain;

import com.opinai.shared.domain.utils.EnumUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenderTest {

    @Test
    public void givenAGender_when_shouldReturnAValueInString() {
        final var expectedGender = "MASCULINO";
        final var male = EnumUtils.of(Gender.values(), expectedGender);
        Assertions.assertEquals(expectedGender, male.getValue());
    }
}
