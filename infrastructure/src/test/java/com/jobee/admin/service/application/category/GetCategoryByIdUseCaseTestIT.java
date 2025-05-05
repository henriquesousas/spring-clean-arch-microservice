package com.jobee.admin.service.application.category;

import com.jobee.admin.service.IntegrationTest;
import com.jobee.admin.service.application.category.retrieve.GetCategoryByIdUseCase;
import com.jobee.admin.service.application.category.retrieve.GetCategoryOutput;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepositoryGateway;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.validation.handler.Notification;
import com.jobee.admin.service.infrastructure.category.repository.CategoryModel;
import com.jobee.admin.service.infrastructure.category.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

@IntegrationTest
public class GetCategoryByIdUseCaseTestIT {

    @Autowired
    private GetCategoryByIdUseCase sut;

    @Autowired
    private CategoryRepository repository;

    @SpyBean
    private CategoryRepositoryGateway categoryRepositoryGateway;

    @Test
    public void givenInvalidCategoryId_whenCallsGetCategory_thenReturnNotification() {

        final var invalidCategoryId = CategoryId.from("123");
        final var expectedError = NotFoundException.with(Category.class, invalidCategoryId);

        Notification notification = Assertions
                .assertDoesNotThrow(() -> this.sut.execute(invalidCategoryId.getValue()).getLeft());

        Assertions.assertEquals(0, repository.count());

        Assertions.assertTrue(notification.hasError());
        Assertions.assertEquals(notification.getErrors().size(), 1);
        Assertions.assertEquals(notification.firstError().message(), expectedError.getMessage());
    }

    @Test
    public void givenAValidCategoryId_whenCallsGetCategoryAndRepositoryThrowsAnError_thenReturnNotification() {

        final var invalidCategoryId = CategoryId.unique();

        Mockito.doThrow(NotFoundException.with(new Error("any")))
                .when(categoryRepositoryGateway).findById(any());

        Notification notification = this.sut.execute(invalidCategoryId.getValue()).getLeft();

        Assertions.assertEquals(0, repository.count());

        Assertions.assertTrue(notification.hasError());
        Assertions.assertEquals(notification.getErrors().size(), 1);
        Assertions.assertEquals(notification.firstError().message(), "any");
    }

    @Test
    public void givenAValidCategory_whenCallsFindById_thenReturnCategory() {

        Category movie = Category.newCategory("Filmes", "any", true);

        Assertions.assertEquals(0, repository.count());

        save(
            movie,
            Category.newCategory("Series", "any", true),
            Category.newCategory("Documentarios", "any", true)
        );

        Assertions.assertEquals(3, repository.count());

        GetCategoryOutput currentCategory = this.sut.execute(movie.getId().getValue()).get();

        Assertions.assertEquals(currentCategory.id().getValue(), movie.getId().getValue());
        Assertions.assertEquals(currentCategory.name(), movie.getName());
        Assertions.assertEquals(currentCategory.description(), movie.getDescription());
        Assertions.assertTrue(currentCategory.isActive());
        Assertions.assertNotNull(currentCategory.createdAt());
        Assertions.assertNotNull(currentCategory.updatedAt());
        Assertions.assertNull(currentCategory.deletedAt());
    }

    private void save(Category... category) {
        this.repository.saveAllAndFlush(
                Arrays.stream(category)
                        .map(CategoryModel::from)
                        .toList()
        );
    }
}
