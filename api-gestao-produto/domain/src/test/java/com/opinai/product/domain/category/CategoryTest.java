package com.opinai.product.domain.category;

import br.com.opinai.api.gestao.produto.domain.category.Category;
import br.com.opinai.api.gestao.produto.domain.category.CategoryBuilder;
import com.opinai.product.domain.UnitTest;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.ValidationException;
import com.opinai.shared.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest  extends UnitTest {


    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateANewCategory() {
        var expectedName = "Marketing";
        var expectedDescription = "Tudo sobre marketing";
        var expectedIsActive = false;

        Category category = new CategoryBuilder(expectedName, expectedDescription)
                .withActive(expectedIsActive)
                .build();

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
    public void givenAnNullName_whenCallNewCategory_thenShouldReceiverAnError() {
        String expectedName = null;
        var expectedDescription = "Tudo sobre marketing";
        var expectedErrorMessage = "'name' should not be null";
        var expectedErrorCount = 1;

        Category sut = new CategoryBuilder(expectedName, expectedDescription)
                .build();

        sut.validate(sut.getNotification());
        Assertions.assertEquals(expectedErrorMessage, sut.getNotification().getFirstError().message());
        Assertions.assertEquals(expectedErrorCount, sut.getNotification().getErrors().size());
    }

    @Test
    public void givenAnEmptyName_whenCallNewCategory_thenShouldReceiverAnError() {
        var expectedName = "";
        var expectedDescription = "Tudo sobre marketing";
        var expectedErrorMessage = "'name' should not be null or empty";
        var expectedErrorCount = 1;

        Category category = new CategoryBuilder(expectedName, expectedDescription)
                .build();

        final var actualException = Assertions.assertThrows(ValidationException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenANameLessThan3Characters_whenCallNewCategory_thenShouldReceiverAnError() {
        var expectedName = "Ad ";
        var expectedDescription = "Tudo sobre marketing";
        var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        var expectedErrorCount = 1;


        Category category = new CategoryBuilder(expectedName, expectedDescription)
                .build();


        final var actualException = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenANameLengthMoreThan3Characters_whenCallNewCategory_thenShouldReceiverAnError() {
        var expectedName = "Ad ".repeat(255);
        var expectedDescription = "Tudo sobre marketing";
        var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        var expectedErrorCount = 1;

        Category category = new CategoryBuilder(expectedName, expectedDescription)
                .build();


        final var actualException = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnCategory_whenCallDeactivate_thenShouldReceiverCategory() throws InterruptedException {
        var expectedName = "Marketing";
        var expectedDescription = "lorem ipsum";

        Category category = new CategoryBuilder(expectedName, expectedDescription)
                .build();

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
    public void givenAnCategory_whenCallActivate_thenShouldReceiverCategory() throws InterruptedException {
        var expectedName = "Marketing";
        var expectedDescription = "lorem ipsum";

        Category category = new CategoryBuilder(expectedName, expectedDescription)
                .withActive(false)
                .build();

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
    public void givenAnCategory_whenCallUpdate_thenShouldReceiverAnUpdatedCategory() throws InterruptedException {
        var expectedName = "Bussiness";
        var expectedDescription = "lorem ipsum";


        Category category = new CategoryBuilder("Marketing", "des")
                .build();

        final var updated = category.getUpdatedAt();
        Assertions.assertTrue(category.isActive());

        Thread.sleep(0, 1);
        final var updatedCategory = category.update(expectedName, expectedDescription);

        Assertions.assertEquals(expectedName, updatedCategory.getName());
        Assertions.assertEquals(expectedDescription, updatedCategory.getDescription());
        Assertions.assertNotNull(updatedCategory.getCreatedAt());
        Assertions.assertTrue(updatedCategory.getUpdatedAt().isAfter(updated));
    }
}
