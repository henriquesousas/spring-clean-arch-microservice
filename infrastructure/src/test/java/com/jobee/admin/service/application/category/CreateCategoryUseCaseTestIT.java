package com.jobee.admin.service.application.category;

import com.jobee.admin.service.IntegrationTest;
import com.jobee.admin.service.application.category.cretate.CreateCategoryInputDto;
import com.jobee.admin.service.application.category.cretate.CreateCategoryUseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryRepositoryGateway;
import com.jobee.admin.service.infrastructure.category.repository.CategoryModel;
import com.jobee.admin.service.infrastructure.category.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.ArgumentMatchers.any;

@IntegrationTest
public class CreateCategoryUseCaseTestIT {

    @Autowired
    private CreateCategoryUseCase sut;

    @Autowired
    private CategoryRepository repository;

    @SpyBean
    private CategoryRepositoryGateway categoryRepositoryGateway;


    @Test
    public void giveAnValidCommand_whenCallCreateCategoryUseCase_thenShouldReturnCategoryId() {

        final var expectedCategory = Category.newCategory("any name", "any description", true);

        Assertions.assertEquals(repository.count(), 0);

        final var categoryId = sut.execute(CreateCategoryInputDto.with(
                expectedCategory.getName(),
                expectedCategory.getDescription())).get();


        Assertions.assertEquals(repository.count(), 1);
        Assertions.assertNotNull(categoryId);
        Assertions.assertNotNull(categoryId.categoryId());

        CategoryModel actualCategory = repository.findById(categoryId.categoryId()).get();
        Assertions.assertEquals(actualCategory.getName(), expectedCategory.getName());
        Assertions.assertEquals(actualCategory.getDescription(), expectedCategory.getDescription());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void giveAnInvalidName_whenCallsCreateCategoryUseCase_thenShouldReturnNotificationError() {

        final var expectedError = "'name' should not be null or empty";
        final var expectedCategory = Category.newCategory("", "any description", true);

        final var notification = sut.execute(CreateCategoryInputDto.with(
                expectedCategory.getName(),
                expectedCategory.getDescription())).getLeft();

        Assertions.assertEquals(notification.getErrors().size(), 1);
        Assertions.assertEquals(notification.firstError().message(), expectedError);

        Assertions.assertEquals(repository.count(), 0);

        Mockito.verify(categoryRepositoryGateway, Mockito.times(0)).create(any());
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_thenShouldReturnException() {

        final var expectedError = "any error";
        final var expectedCategory = Category.newCategory("any name", "any description", true);

        Mockito.doThrow(new IllegalArgumentException(expectedError))
                        .when(categoryRepositoryGateway).create(any());

        final var notification = sut.execute(CreateCategoryInputDto.with(
                expectedCategory.getName(),
                expectedCategory.getDescription())).getLeft();

        Assertions.assertEquals(notification.getErrors().size(), 1);
        Assertions.assertEquals(notification.firstError().message(), expectedError);

        Assertions.assertEquals(repository.count(), 0);
    }
}
