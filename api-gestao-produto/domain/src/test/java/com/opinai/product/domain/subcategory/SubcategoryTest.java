package com.opinai.product.domain.subcategory;

import br.com.opinai.api.gestao.produto.domain.subcategory.Subcategory;
import br.com.opinai.api.gestao.produto.domain.subcategory.SubcategoryBuilder;
import br.com.opinai.api.gestao.produto.domain.subcategory.SubcategoryId;
import com.opinai.product.domain.UnitTest;
import br.com.opinai.api.gestao.produto.domain.category.CategoryId;
import com.opinai.shared.domain.utils.InstantUtils;
import com.opinai.shared.domain.validation.handler.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SubcategoryTest extends UnitTest {

    @Test
    public void givenAValidGenreValues_whenBuildGenre_shouldCreateANewGenre() {

        final var expectedName = "Action";
        final var expectedNow = InstantUtils.now();
        final var expectedGenreId = SubcategoryId.unique();
        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());

        final var builder = new SubcategoryBuilder(expectedName, expectedCategoryIds)
                .withId(expectedGenreId)
                .withCreatedAt(expectedNow)
                .withActive(true);

        final var sut = Subcategory.newGenre(builder);

        Assertions.assertEquals(sut.getAggregateId().getValue(), expectedGenreId.getValue());
        Assertions.assertEquals(sut.getName(), expectedName);

        Assertions.assertTrue(sut.isActive());
        Assertions.assertEquals(sut.getCreatedAt(), expectedNow);
        Assertions.assertEquals(sut.getUpdatedAt(), expectedNow);
        Assertions.assertNull(sut.getDeletedAt());
    }

    @Test
    public void givenADefaultGenreValues_whenBuildGenre_shouldCreateANewGenre() {

        final var expectedName = "Action";
        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());

        final var builder = new SubcategoryBuilder(expectedName, expectedCategoryIds);
        final var sut = Subcategory.newGenre(builder);

        Assertions.assertNotNull(sut.getAggregateId().getValue());
        Assertions.assertEquals(sut.getName(), expectedName);

        Assertions.assertTrue(sut.isActive());
        Assertions.assertNotNull(sut.getCreatedAt());
        Assertions.assertNotNull(sut.getUpdatedAt());
        Assertions.assertEquals(sut.getCreatedAt(), sut.getUpdatedAt());
        Assertions.assertNull(sut.getDeletedAt());
    }

    @Test
    public void givenAnInvalidGenreValues_whenBuildGenre_shouldHaveANotificationErrors() {

        final var expectedErrors = List.of(
                "'description' should not be null or empty",
                "'description' should be at least 3 characters",
                "'name' should not be null or empty",
                "'name' should be at least 3 characters",
                "GenreId must be a valid UUID",
                "'Categories' should not be null or empty"
        );


        final var sut = new SubcategoryBuilder("", List.of(CategoryId.unique()))
                .withId(SubcategoryId.from("123")).build();


        final var notification = Notification.create();
        sut.validate(notification);

        Assertions.assertTrue(notification.hasError());
        Assertions.assertEquals(notification.getErrors().size(), 5);

        notification.getErrors().forEach(error -> {
            Assertions.assertTrue(
                    expectedErrors.contains(error.message()),
                    "Expected error not found: " + error.message()
            );
        });
    }

    @Test
    public void givenAValidGenreValues_whenUpdate_shouldChangeValues() {

        final var expectedName = "Action";
        final var expectedChangedName = "Action changed";
        final var categoryId1 = CategoryId.unique();
        final var categoryId2 = CategoryId.unique();
        final var expectedCategoryIds = List.of(categoryId1);

        final var builder = new SubcategoryBuilder(expectedName, expectedCategoryIds);

        final var sut = Subcategory.newGenre(builder);

        Assertions.assertEquals(sut.getName(), expectedName);
        Assertions.assertEquals(sut.getCreatedAt(), sut.getUpdatedAt());

        sut.update(expectedChangedName, List.of(categoryId2));

        Assertions.assertEquals(sut.getName(), expectedChangedName);
        Assertions.assertTrue(sut.getCreatedAt().isBefore(sut.getUpdatedAt()));
        Assertions.assertNull(sut.getDeletedAt());
        Assertions.assertEquals(sut.getCategories().size(), 2);
    }

    @Test
    public void givenAValidGenreValues_whenUpdateWithInvalidValues_shouldNotificationError() {

        final var expectedName = "Action";
        final var expectedChangedName = "";

        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());

        final var builder = new SubcategoryBuilder(expectedName,  expectedCategoryIds);
        final var sut = Subcategory.newGenre(builder);

        Assertions.assertEquals(sut.getName(), expectedName);
        Assertions.assertEquals(sut.getCreatedAt(), sut.getUpdatedAt());

        sut.update(expectedChangedName, List.of());

        Assertions.assertEquals(sut.getNotification().getErrors().size(), 4);
        Assertions.assertEquals(sut.getName(), expectedChangedName);
        Assertions.assertTrue(sut.getCreatedAt().isBefore(sut.getUpdatedAt()));
        Assertions.assertNull(sut.getDeletedAt());
    }

    @Test
    public void givenAnInactiveGenre_whenCallsUpdate_shouldNotificationError() {

        final var expectedName = "Action";
        final var expectedChangedName = "";

        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());
        final var builder = new SubcategoryBuilder(expectedName,  expectedCategoryIds)
                .withActive(false);

        final var sut = Subcategory.newGenre(builder);

        sut.update(expectedChangedName,  List.of());
        Assertions.assertEquals(sut.getNotification().getErrors().size(), 1);

        Assertions.assertEquals(sut.getName(), expectedName);
        Assertions.assertEquals(sut.getCreatedAt(), sut.getUpdatedAt());
    }

    @Test
    public void givenAnValidGenre_whenCallsDeactivate_shouldDeactivateWithSuccess() {

        final var expectedName = "Action";
        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());

        final var builder = new SubcategoryBuilder(expectedName,  expectedCategoryIds);

        final var sut = Subcategory.newGenre(builder);

        sut.deactivate();

        Assertions.assertEquals(sut.getNotification().getErrors().size(), 0);
        Assertions.assertEquals(sut.getName(), expectedName);
        Assertions.assertTrue(sut.getCreatedAt().isBefore(sut.getUpdatedAt()));
        Assertions.assertEquals(sut.getDeletedAt(), sut.getUpdatedAt());
        Assertions.assertFalse(sut.isActive());
    }

    @Test
    public void givenAnValidGenre_whenCallsUpdateWithTheSameCategoryId_shouldNotDuplicateValues() {

        final var expectedName = "Action";
        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());

        final var builder = new SubcategoryBuilder(expectedName, expectedCategoryIds);

        final var sut = Subcategory.newGenre(builder);

        sut.update(expectedName, expectedCategoryIds);

        Assertions.assertEquals(sut.getNotification().getErrors().size(), 0);
        Assertions.assertEquals(sut.getCategories().size(), 2);
        Assertions.assertEquals(sut.getName(), expectedName);
        Assertions.assertTrue(sut.getCreatedAt().isBefore(sut.getUpdatedAt()));
        Assertions.assertNull(sut.getDeletedAt());
        Assertions.assertTrue(sut.isActive());
    }

    @Test
    public void givenAnValidGenre_whenCallsRemoveCategoryId_shouldUpdateListCategories() {

        final var cat1 = CategoryId.unique();
        final var cat2 = CategoryId.unique();
        final var expectedCategoryIds = List.of(cat1, cat2);

        final var builder = new SubcategoryBuilder(
                "Action",
                expectedCategoryIds);

        final var sut = Subcategory.newGenre(builder);

        Assertions.assertEquals(sut.getCategories().size(), 2);

        sut.removeCategory(cat2);

        Assertions.assertEquals(sut.getCategories().size(), 1);
        Assertions.assertEquals(sut.getCategories().get(0).getValue(), cat1.getValue());
        Assertions.assertTrue(sut.getCreatedAt().isBefore(sut.getUpdatedAt()));
        Assertions.assertNull(sut.getDeletedAt());
    }

    @Test
    public void givenAnDeactivatedGenre_whenCallsActivate_shouldActivateWithSuccess() {

        final var expectedName = "Action";
        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());

        final var builder = new SubcategoryBuilder(expectedName, expectedCategoryIds)
                .withActive(false);

        final var sut = Subcategory.newGenre(builder);

        sut.activate();

        Assertions.assertEquals(sut.getNotification().getErrors().size(), 0);
        Assertions.assertEquals(sut.getName(), expectedName);
        Assertions.assertTrue(sut.getCreatedAt().isBefore(sut.getUpdatedAt()));
        Assertions.assertNull(sut.getDeletedAt());
        Assertions.assertTrue(sut.isActive());
    }

    @Test
    public void givenAValidIdGenre_whenBuildGenre_shouldCreateANewGenre() {
;
        final var expectedGenreId = SubcategoryId.unique();

        final var builder = new SubcategoryBuilder("Action",List.of(CategoryId.unique()))
                .withId(expectedGenreId);

        final var sut = Subcategory.newGenre(builder);

        Assertions.assertEquals(sut.getAggregateId().getValue(), expectedGenreId.getValue());
    }
}
