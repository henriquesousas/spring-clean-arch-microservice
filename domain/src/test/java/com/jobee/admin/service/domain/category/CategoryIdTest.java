package com.jobee.admin.service.domain.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryIdTest {


    @Test
    public void givenAnEmptyCategoryId_whenCreate_shouldNotificationWithErrors()    {
        CategoryId categoryId = CategoryId.from("");
        Assertions.assertTrue(categoryId.getNotification().hasError());
        Assertions.assertEquals(categoryId.getNotification().getErrors().size(), 2);
        Assertions.assertEquals(categoryId.getNotification().getErrors().get(0).message(), "CategoryId cannot be null or empty");
        Assertions.assertEquals(categoryId.getNotification().getErrors().get(1).message(), "CategoryId must be a valid UUID");
    }

    @Test
    public void givenAnInvalidCategoryId_whenCreate_shouldNotificationWithErrors()    {
        CategoryId categoryId = CategoryId.from("123");
        Assertions.assertTrue(categoryId.getNotification().hasError());
        Assertions.assertEquals(categoryId.getNotification().getErrors().size(), 1);
        Assertions.assertEquals(categoryId.getNotification().getFirstError().message(), "CategoryId must be a valid UUID");
    }
}
