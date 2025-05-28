package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.valueobjects.points.StrongPoints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class StrongPointsTest {

    @Test
    public void givenAnNullStrongPoint_whenInstantiate_shouldNoError() {
        final var strongPoints = StrongPoints.of(null);
        Assertions.assertNotNull(strongPoints);
        Assertions.assertEquals(strongPoints.getValue().size(), 0);
    }

    @Test
    public void givenAnEmptyStrongPoint_whenAddOneWithMoreThanAllowedCharacter_shouldNotifyError() {
        final var strongPoints = StrongPoints.of(Set.of(
                "123",
                "1234567891234567892123456789301"
        ));
        Assertions.assertNotNull(strongPoints);
        Assertions.assertEquals(strongPoints.getValue().size(), 2);
        Assertions.assertTrue(strongPoints.getNotification().hasError());
    }

    @Test
    public void givenAnEmptyPoint_whenAdd_shouldNotBeEmpty() {
        final var strongPoints = StrongPoints.of(null);

        strongPoints.add("1234");

        Assertions.assertNotNull(strongPoints);
        Assertions.assertEquals(strongPoints.getValue().size(), 1);
        Assertions.assertFalse(strongPoints.getNotification().hasError());
    }

    @Test
    public void givenAnEmptyPoint_whenAddRepeated_shouldNotIncrement() {
        final var strongPoints = StrongPoints.of(null);

        strongPoints.add("1234");
        strongPoints.add("4567");
        strongPoints.add("1234");
        strongPoints.add("1234");
        strongPoints.add("1234");

        Assertions.assertNotNull(strongPoints);
        Assertions.assertEquals(strongPoints.getValue().size(), 2);
        Assertions.assertFalse(strongPoints.getNotification().hasError());
    }

    @Test
    public void givenAnEmptyPoint_whenAddMoreThanLimit_shouldNotifyError() {
        final var strongPoints = StrongPoints.of(null);

        for (int i = 0; i < 33; i++) {
            strongPoints.add("Point " + i);
        }

        Assertions.assertNotNull(strongPoints);
        Assertions.assertEquals(strongPoints.getNotification().getErrors().size(), 1);
        Assertions.assertEquals(strongPoints.getNotification().getErrors().get(0).message(), "Pontos forte execedeu o limite");
        Assertions.assertTrue(strongPoints.getNotification().hasError());


    }

    @Test
    public void givenValidPoints_whenRemove_shouldDelete() {
        final var points = StrongPoints.of(null);

        points.add("123");
        points.add("456");

        points.remove("123");

        Assertions.assertNotNull(points);
        Assertions.assertFalse(points.getNotification().hasError());
        Assertions.assertEquals(points.getValue().size(), 1);
    }

    @Test
    public void givenNotStoragePoint_whenRemove_shouldNotifyError() {
        final var points = StrongPoints.of(null);

        points.add("123");
        points.add("456");

        points.remove("789");

        Assertions.assertNotNull(points);
        Assertions.assertEquals(points.getValue().size(), 2);
        Assertions.assertTrue(points.getNotification().hasError());
        Assertions.assertEquals(points.getNotification().getFirstError().message(), "789 não encontrado");
    }

    @Test
    public void givenValidPoints_whenCallsAsString_shouldReturnString() {
        final var points = StrongPoints.of(null);

        points.add("123");
        points.add("456");

        final var str = points.asString();
        Assertions.assertEquals(str, "123,456");

    }

    @Test
    public void givenValidStrPoints_whenCallsAsSet_shouldReturnSet() {
        final var points = StrongPoints.of(null);
        final var value = "123,456";

        points.asSet(value);
        Assertions.assertEquals(points.getValue().size(), 2);

    }
}
