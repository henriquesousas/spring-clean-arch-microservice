package com.jobee.admin.service.application.category;

import com.jobee.admin.service.IntegrationTest;
import com.jobee.admin.service.application.Unit;
import com.jobee.admin.service.application.category.delete.DeleteCategoryUseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepositoryGateway;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.validation.handler.Notification;
import com.jobee.admin.service.infrastructure.category.repository.CategoryModel;
import com.jobee.admin.service.infrastructure.category.repository.CategoryRepository;
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
    private CategoryRepository repository;

    @SpyBean
    private CategoryRepositoryGateway categoryRepositoryGateway;

    @Test
    public void giveAnInvalidCategoryId_whenCallDeleteCategory_thenShouldReturnNotification() {

        final var categoryId = CategoryId.unique();
        final var expectedError = NotFoundException.with(Category.class, categoryId);

        Notification notification = this.sut.execute(categoryId).getLeft();

        Assertions.assertEquals(0, repository.count());

        Assertions.assertTrue(notification.hasError());
        Assertions.assertEquals(notification.getErrors().size(), 1);
        Assertions.assertEquals(notification.firstError().message(), expectedError.getMessage());

        Mockito.verify(categoryRepositoryGateway, times(1)).findById(any());
        Mockito.verify(categoryRepositoryGateway, times(0)).delete(any());
    }

    @Test
    public void giveAnValidCategoryId_whenCallDeleteCategory_thenShouldSuccess() {

        Category category = Category.newCategory("any name", "any des", true);

        this.repository.save(CategoryModel.from(category));

        Assertions.assertEquals(1, repository.count());

        Unit unit = this.sut.execute(category.getId()).get();

        Assertions.assertEquals(0, repository.count());
        Assertions.assertInstanceOf(Unit.class, unit);
    }
}
