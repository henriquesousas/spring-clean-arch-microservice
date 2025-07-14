package com.opinai.product.domain.genre;

import com.opinai.product.domain.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GenreIdTest  extends UnitTest {

    @Test
    public void givenAInvalidGenreId_whenCallsNewGenreId_thenShouldReturnNotification() {
        //given
        final String invalidGenreId = "123";
        final var expectedErrorMessage = "GenreId must be a valid UUID";

        //when
        GenreId sut = GenreId.from(invalidGenreId);

        //then
        Assertions.assertTrue(sut.getNotification().hasError());
        //TODO: here
//        Assertions.assertEquals(sut.getNotification().getErrors().get(0).message(), expectedErrorMessage);
    }

    @Test
    public void givenAEmptyGenreId_whenCallsNewGenreId_thenShouldReturnNotification() {
        //given
        final String invalidGenreId = "";
        final var expectedErrors = List.of(
                "GenreId cannot be null or empty",
                "GenreId must be a valid UUID");

        //when
        GenreId sut = GenreId.from(invalidGenreId);

        //then
        Assertions.assertTrue(sut.getNotification().hasError());
        Assertions.assertEquals(sut.getNotification().getErrors().size(), 2);
        sut.getNotification().getErrors().forEach(error -> {
            Assertions.assertTrue(expectedErrors.contains(error.message()) );
        });
    }
}
