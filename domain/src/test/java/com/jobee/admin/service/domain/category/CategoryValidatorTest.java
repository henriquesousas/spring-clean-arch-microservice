package com.jobee.admin.service.domain.category;

import com.jobee.admin.service.domain.validation.handler.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryValidatorTest {

    @Test
    public void givenACategoryWithInvalidParams_whenCallsValidation_shouldReturnNotificationWith3Errors() {
        Category category = new CategoryBuilder("", "")
                .withCategoryId("123")
                .build();

        Notification notification = Notification.create();
        new CategoryValidator(category, notification).validate();

        Assertions.assertTrue(notification.hasError());
        Assertions.assertEquals(notification.getErrors().size(),4);
    }

}
