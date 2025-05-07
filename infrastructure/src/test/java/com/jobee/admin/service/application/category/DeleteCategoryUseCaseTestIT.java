package com.jobee.admin.service.application.category;

import com.jobee.admin.service.IntegrationTest;
import com.jobee.admin.service.application.Unit;
import com.jobee.admin.service.application.category.delete.DeleteCategoryUseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryBuilder;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.validation.handler.Notification;
import com.jobee.admin.service.infrastructure.category.repository.CategoryJpaRepository;
import com.jobee.admin.service.infrastructure.category.repository.CategoryModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@IntegrationTest
public class DeleteCategoryUseCaseTestIT {

    @Autowired
    private DeleteCategoryUseCase sut;

    @Autowired
    private CategoryJpaRepository repository;

    @SpyBean
    private CategoryRepository categoryRepositoryGateway;

    @Test
    public void givenAnInvalidCategoryId_whenCallDeleteCategory_thenShouldReturnNotification() {

        final var categoryId = CategoryId.unique();
        final var expectedError = NotFoundException.with(Category.class, categoryId);

        Notification notification = this.sut.execute(categoryId.getValue()).getLeft();

        Assertions.assertEquals(0, repository.count());

        Assertions.assertTrue(notification.hasError());
        Assertions.assertEquals(notification.getErrors().size(), 1);
        Assertions.assertEquals(notification.getFirstError().message(), expectedError.getMessage());

        Mockito.verify(categoryRepositoryGateway, times(1)).findById(any());
        Mockito.verify(categoryRepositoryGateway, times(0)).delete(any());
    }

    @Test
    public void givenAnValidCategoryId_whenCallDeleteCategory_thenShouldSuccess() {

        Category category = CategoryBuilder.newCategory("any name", "any des").build();

        this.repository.save(CategoryModel.from(category));

        Assertions.assertEquals(1, repository.count());

        Unit unit = this.sut.execute(category.getId().getValue()).get();

        Assertions.assertEquals(0, repository.count());
        Assertions.assertInstanceOf(Unit.class, unit);
    }
}
