package com.jobee.admin.service.application.category;

import com.jobee.admin.service.IntegrationTest;
import com.jobee.admin.service.application.usecases.category.create.CreateCategoryInputDto;
import com.jobee.admin.service.application.usecases.category.create.CreateCategoryUseCase;
import com.jobee.admin.service.domain.category.CategoryBuilder;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.infrastructure.category.repository.CategoryJpaRepository;
import com.jobee.admin.service.infrastructure.category.repository.CategoryModel;
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
    private CategoryJpaRepository repository;

    @SpyBean
    private CategoryRepository categoryRepositoryGateway;


    @Test
    public void giveAnValidCommand_whenCallCreateCategoryUseCase_thenShouldReturnCategoryId() {

        final var expectedCategory = CategoryBuilder.newCategory("any name", "any description").build();

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
    public void givenAnInvalidName_whenCallsCreateCategoryUseCase_thenShouldDomainException() {

        final var expectedError = "'name' should not be null or empty";
        final var expectedCategory = CategoryBuilder.newCategory("", "any description").build();

        final var notification = sut.execute(CreateCategoryInputDto.with(
                expectedCategory.getName(),
                expectedCategory.getDescription())).getLeft();

        Assertions.assertEquals(notification.getErrors().size(), 2);
//        Assertions.assertEquals(notification.getMessage(), expectedError);

        Assertions.assertEquals(repository.count(), 0);

        Mockito.verify(categoryRepositoryGateway, Mockito.times(0)).create(any());
    }

//    @Test
//    public void givenAValidCommand_whenGatewayThrowsRandomException_thenShouldReturnException() {
//
//        final var expectedError = "any error";
//        final var expectedCategory = CategoryBuilder.newCategory("any name", "any description").build();
//
//        Mockito.doThrow(new IllegalArgumentException(expectedError))
//                        .when(categoryRepositoryGateway).create(any());
//
//        final var notification = sut.execute(CreateCategoryInputDto.with(
//                expectedCategory.getName(),
//                expectedCategory.getDescription())).getLeft();
//
//        Assertions.assertEquals(notification.getErrors().size(), 1);
////        Assertions.assertEquals(notification.getMessage(), expectedError);
//
//        Assertions.assertEquals(repository.count(), 0);
//    }
}
