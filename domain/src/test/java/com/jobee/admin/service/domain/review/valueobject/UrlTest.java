package com.jobee.admin.service.domain.review.valueobject;

import com.jobee.admin.service.domain.review.valueobjects.Url;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UrlTest {

    @Test
    public void givenAValidUrl_whenInstantiate_shouldHaveNoError() {
        final var expectedLink = "https://www.reclameaqui.com.br/";
        final var actualLink = Url.from(expectedLink);
        Assertions.assertFalse(actualLink.getNotification().hasError());
        Assertions.assertEquals(actualLink.getValue(), expectedLink);
    }

    @Test
    public void givenAnInvalidUrl_whenInstantiate_shouldHaveError() {
        final var expectedLink = "123";
        final var actualLink = Url.from(expectedLink);
        Assertions.assertTrue(actualLink.getNotification().hasError());
        Assertions.assertEquals(actualLink.getNotification().getFirstError().message(), "Url inválida");
        Assertions.assertEquals(actualLink.getValue(), expectedLink);
    }
}
