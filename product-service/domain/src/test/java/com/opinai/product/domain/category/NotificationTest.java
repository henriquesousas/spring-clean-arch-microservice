package com.opinai.product.domain.category;

import com.opinai.product.domain.UnitTest;
import com.opinai.shared.domain.validation.Error;
import com.opinai.shared.domain.validation.handler.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NotificationTest  extends UnitTest {

    @Test
    public void shouldCreateANotificationDomainError() {
        final var sut = Notification.create();
        sut.append(new Error("error1"));
        sut.append(new Error("error2"));

        Assertions.assertEquals(sut.getErrors().size(), 2);
    }

    @Test
    public void shouldCopyNotificationDomainErrorToAnotherNotificationError() {
        final var sut = Notification.create();
        final var expectedNotification = Notification.create();

        expectedNotification.append(new Error("error1"));
        expectedNotification.append(new Error("error2"));

        sut.copy(expectedNotification);

        Assertions.assertEquals(sut.getErrors().size(), 2);
    }
}
