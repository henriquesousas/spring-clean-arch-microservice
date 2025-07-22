package br.com.opinai.api.conta.domain.valueobjects;

import com.opinai.shared.domain.utils.InstantUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BirthDateTest {


    @Test
    public void givenAValidDate_whenCreate_shouldHaveNoError() {
        final var expectedDate = "27/08/1985";
        final var sut =  BirthDate.from (expectedDate);
        Assertions.assertFalse(sut.getNotification().hasError());
        Assertions.assertEquals(sut.getValue(), expectedDate);
    }

    @Test
    public void givenADateLessThan16Age_whenCreate_shouldHaveError() {
        final var expectedError = "Só é possível se cadastrar a partir dos 16 anos :)";
        final var expectedDate = InstantUtils.toDateFormat(InstantUtils.now());
        final var sut =  BirthDate.from(expectedDate);
        Assertions.assertTrue(sut.getNotification().hasError());
        Assertions.assertEquals(sut.getValue(), expectedDate);
        Assertions.assertEquals(expectedError, sut.getNotification().getFirstError().message());
    }

    @Test
    public void givenAnInvalidate_whenCreate_shouldHaveError() {
        final var expectedError = "Data de nascimento inválida";
        final var expectedDate = "123";
        final var sut =  BirthDate.from(expectedDate);
        Assertions.assertTrue(sut.getNotification().hasError());
        Assertions.assertEquals(sut.getValue(), expectedDate);
        Assertions.assertEquals(expectedError, sut.getNotification().getFirstError().message());
    }

}