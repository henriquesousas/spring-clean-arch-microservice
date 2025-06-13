package com.jobee.admin.service.domain.genre;

import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.commons.utils.InstantUtils;
import com.jobee.admin.service.domain.commons.validation.handler.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GenreTest {

    @Test
    public void givenAValidGenreValues_whenBuildGenre_shouldCreateANewGenre() {

        final var expectedName = "Action";
        final var expectedDescription = "Action movies";
        final var expectedNow = InstantUtils.now();
        final var expectedGenreId = GenreId.unique();
        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());

        final var builder = new GenreBuilder(expectedName, expectedDescription, expectedCategoryIds)
                .withId(expectedGenreId)
                .withCreatedAt(expectedNow)
                .withActive(true);

        final var sut = Genre.newGenre(builder);

        Assertions.assertEquals(sut.getAggregateId().getValue(), expectedGenreId.getValue());
        Assertions.assertEquals(sut.getName(), expectedName);
        Assertions.assertEquals(sut.getDescription(), expectedDescription);
        Assertions.assertTrue(sut.isActive());
        Assertions.assertEquals(sut.getCreatedAt(), expectedNow);
        Assertions.assertEquals(sut.getUpdatedAt(), expectedNow);
        Assertions.assertNull(sut.getDeletedAt());
    }

    @Test
    public void givenADefaultGenreValues_whenBuildGenre_shouldCreateANewGenre() {

        final var expectedName = "Action";
        final var expectedDescription = "Action movies";
        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());

        final var builder = new GenreBuilder(expectedName, expectedDescription, expectedCategoryIds);
        final var sut = Genre.newGenre(builder);

        Assertions.assertNotNull(sut.getAggregateId().getValue());
        Assertions.assertEquals(sut.getName(), expectedName);
        Assertions.assertEquals(sut.getDescription(), expectedDescription);
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


        final var sut = new GenreBuilder("", "", List.of(CategoryId.unique()))
                .withId(GenreId.from("123")).build();


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
        final var expectedDescription = "Action movies";
        final var expectedChangedName = "Action changed";
        final var expectedChangedDescription = "Action movies changed";
        final var categoryId1 = CategoryId.unique();
        final var categoryId2 = CategoryId.unique();
        final var expectedCategoryIds = List.of(categoryId1);

        final var builder = new GenreBuilder(expectedName, expectedDescription, expectedCategoryIds);

        final var sut = Genre.newGenre(builder);

        Assertions.assertEquals(sut.getName(), expectedName);
        Assertions.assertEquals(sut.getDescription(), expectedDescription);
        Assertions.assertEquals(sut.getCreatedAt(), sut.getUpdatedAt());

        sut.update(expectedChangedName, expectedChangedDescription, List.of(categoryId2));

        Assertions.assertEquals(sut.getName(), expectedChangedName);
        Assertions.assertEquals(sut.getDescription(), expectedChangedDescription);
        Assertions.assertTrue(sut.getCreatedAt().isBefore(sut.getUpdatedAt()));
        Assertions.assertNull(sut.getDeletedAt());
        Assertions.assertEquals(sut.getCategories().size(), 2);
    }

    @Test
    public void givenAValidGenreValues_whenUpdateWithInvalidValues_shouldNotificationError() {

        final var expectedName = "Action";
        final var expectedDescription = "Action movies";
        final var expectedChangedName = "";
        final var expectedChangedDescription = "";

        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());

        final var builder = new GenreBuilder(expectedName, expectedDescription, expectedCategoryIds);
        final var sut = Genre.newGenre(builder);

        Assertions.assertEquals(sut.getName(), expectedName);
        Assertions.assertEquals(sut.getDescription(), expectedDescription);
        Assertions.assertEquals(sut.getCreatedAt(), sut.getUpdatedAt());

        sut.update(expectedChangedName, expectedChangedDescription, List.of());

        Assertions.assertEquals(sut.getNotification().getErrors().size(), 4);
        Assertions.assertEquals(sut.getName(), expectedChangedName);
        Assertions.assertEquals(sut.getDescription(), expectedChangedDescription);
        Assertions.assertTrue(sut.getCreatedAt().isBefore(sut.getUpdatedAt()));
        Assertions.assertNull(sut.getDeletedAt());
    }

    @Test
    public void givenAnInactiveGenre_whenCallsUpdate_shouldNotificationError() {

        final var expectedName = "Action";
        final var expectedDescription = "Action movies";
        final var expectedChangedName = "";

        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());
        final var builder = new GenreBuilder(expectedName, expectedDescription, expectedCategoryIds)
                .withActive(false);

        final var sut = Genre.newGenre(builder);

        sut.update(expectedChangedName, expectedDescription, List.of());
        Assertions.assertEquals(sut.getNotification().getErrors().size(), 1);
        //TODO: here
//        Assertions.assertEquals(sut.getNotification().getFirstError().message(), "Could not update a deactivated genre");
        Assertions.assertEquals(sut.getName(), expectedName);
        Assertions.assertEquals(sut.getCreatedAt(), sut.getUpdatedAt());
    }

    @Test
    public void givenAnValidGenre_whenCallsDeactivate_shouldDeactivateWithSuccess() {

        final var expectedName = "Action";
        final var expectedDescription = "Action movies";
        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());

        final var builder = new GenreBuilder(expectedName, expectedDescription, expectedCategoryIds);

        final var sut = Genre.newGenre(builder);

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
        final var expectedDescription = "Action movies";
        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());

        final var builder = new GenreBuilder(expectedName, expectedDescription, expectedCategoryIds);

        final var sut = Genre.newGenre(builder);

        sut.update(expectedName, expectedDescription, expectedCategoryIds);

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

        final var builder = new GenreBuilder(
                "Action",
                "Action desc",
                expectedCategoryIds);

        final var sut = Genre.newGenre(builder);

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
        final var expectedDescription = "Action movies";
        final var expectedCategoryIds = List.of(CategoryId.unique(), CategoryId.unique());

        final var builder = new GenreBuilder(expectedName, expectedDescription, expectedCategoryIds)
                .withActive(false);

        final var sut = Genre.newGenre(builder);

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
        final var expectedGenreId = GenreId.unique();

        final var builder = new GenreBuilder("Action","Action desc",List.of(CategoryId.unique()))
                .withId(expectedGenreId);

        final var sut = Genre.newGenre(builder);

        Assertions.assertEquals(sut.getAggregateId().getValue(), expectedGenreId.getValue());
    }
}
