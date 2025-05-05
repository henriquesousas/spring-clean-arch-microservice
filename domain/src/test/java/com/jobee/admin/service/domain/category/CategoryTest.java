package com.jobee.admin.service.domain.category;

import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {


    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateANewCategory() {
        var expectedName = "Marketing";
        var expectedDescription = "Tudo sobre marketing";
        var expectedIsActive = false;

        final var category = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        Assertions.assertNotNull(category);
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(expectedName, category.getName());
        Assertions.assertEquals(expectedDescription, category.getDescription());
        Assertions.assertEquals(expectedIsActive, category.isActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
        Assertions.assertNull(category.getDeletedAt());
    }

    @Test
    public void giveAnNullName_whenCallNewCategory_thenShouldReceiverAnError() {
        String expectedName = null;
        var expectedDescription = "Tudo sobre marketing";
        var expectedIsActive = false;
        var expectedErrorMessage = "'name' should not be null or empty";
        var expectedErrorCount = 1;

        final var category = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        final var actualException = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void giveAnEmptyName_whenCallNewCategory_thenShouldReceiverAnError() {
        var expectedName = "";
        var expectedDescription = "Tudo sobre marketing";
        var expectedIsActive = false;
        var expectedErrorMessage = "'name' should not be null or empty";
        var expectedErrorCount = 1;

        final var category = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        final var actualException = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void giveANameLessThan3Characters_whenCallNewCategory_thenShouldReceiverAnError() {
        var expectedName = "Ad ";
        var expectedDescription = "Tudo sobre marketing";
        var expectedIsActive = false;
        var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        var expectedErrorCount = 1;

        final var category = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        final var actualException = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void giveANameLengthMoreThan3Characters_whenCallNewCategory_thenShouldReceiverAnError() {
        var expectedName = "Ad ".repeat(255);
        var expectedDescription = "Tudo sobre marketing";
        var expectedIsActive = false;
        var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        var expectedErrorCount = 1;

        final var category = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        final var actualException = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void giveAnEmptyDescription_whenCallNewCategory_thenShouldNotThrowAnDomainException() {
        var expectedName = "Marketing";
        var expectedDescription = "";
        var expectedIsActive = false;

        final var category = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedName, category.getName());
        Assertions.assertEquals(expectedDescription, category.getDescription());
        Assertions.assertEquals(expectedIsActive, category.isActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
        Assertions.assertNull(category.getDeletedAt());
    }

    @Test
    public void giveAnCategory_whenCallDeactivate_thenShouldReceiverCategory() throws InterruptedException {
        var expectedName = "Marketing";
        var expectedDescription = "lorem ipsum";

        final var category = Category.newCategory(
                expectedName,
                expectedDescription,
                true
        );

        final var updated = category.getUpdatedAt();
        Assertions.assertTrue(category.isActive());
        Assertions.assertNull(category.getDeletedAt());
        Assertions.assertEquals(category.getUpdatedAt(), updated);

        Thread.sleep(0, 1);
        final var updatedCategory = category.deactivate();

        Assertions.assertDoesNotThrow(() -> updatedCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(updatedCategory.getName(), expectedName);
        Assertions.assertEquals(updatedCategory.getDescription(), expectedDescription);
        Assertions.assertFalse(updatedCategory.isActive());
        Assertions.assertNotNull(updatedCategory.getCreatedAt());
        Assertions.assertTrue(updatedCategory.getUpdatedAt().isAfter(updated));
    }

    @Test
    public void giveAnCategory_whenCallActivate_thenShouldReceiverCategory() throws InterruptedException {
        var expectedName = "Marketing";
        var expectedDescription = "lorem ipsum";
        final var category = Category.newCategory(
                expectedName,
                expectedDescription,
                false
        );

        final var updated = category.getUpdatedAt();

        Assertions.assertFalse(category.isActive());
        Assertions.assertNull(category.getDeletedAt());

        Thread.sleep(0, 1);
        final var updatedCategory = category.activate();
        final var afterUpdated = updatedCategory.getUpdatedAt();

        Assertions.assertDoesNotThrow(() -> updatedCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertTrue(updatedCategory.isActive());
        Assertions.assertEquals(updatedCategory.getName(), expectedName);
        Assertions.assertEquals(updatedCategory.getDescription(), expectedDescription);
        Assertions.assertNotNull(updatedCategory.getCreatedAt());
        Assertions.assertTrue(afterUpdated.isAfter(updated));

    }

    @Test
    public void giveAnCategory_whenCallUpdate_thenShouldReceiverAnUpdatedCategory() throws InterruptedException {
        var expectedName = "Bussiness";
        var expectedDescription = "lorem ipsum";
        final var category = Category.newCategory(
                "Marketing",
                "des",
                true
        );

        final var updated = category.getUpdatedAt();
        Assertions.assertTrue(category.isActive());

        Thread.sleep(0,1);
        final var updatedCategory = category.update(expectedName, expectedDescription);

        Assertions.assertEquals(expectedName, updatedCategory.getName());
        Assertions.assertEquals(expectedDescription, updatedCategory.getDescription());
        Assertions.assertNotNull(updatedCategory.getCreatedAt());
        Assertions.assertTrue(updatedCategory.getUpdatedAt().isAfter(updated));
    }
}
