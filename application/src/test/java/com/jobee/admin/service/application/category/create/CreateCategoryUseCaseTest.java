package com.jobee.admin.service.application.category.create;

import com.jobee.admin.service.application.category.cretate.CreateCategoryInputDto;
import com.jobee.admin.service.application.category.cretate.CreateCategoryUseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryRepositoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @InjectMocks
    private CreateCategoryUseCase sut;

    @Mock
    private CategoryRepositoryGateway repository;

    @BeforeEach
    public void cleanup() {
        Mockito.reset(repository);
    }


    @Test
    public void giveAnValidCommand_whenCallCreateCategoryUseCase_thenShouldReturnCategoryId() {

        final var expectedCategory = Category.newCategory("any name", "any description", true);

        when(repository.create(any()))
                .thenReturn(expectedCategory);

        final var categoryOutputDto = sut.execute(CreateCategoryInputDto.with(
                expectedCategory.getName(),
                expectedCategory.getDescription())).get();

        Assertions.assertNotNull(categoryOutputDto);
        Assertions.assertNotNull(categoryOutputDto.categoryId());

        Mockito.verify(repository, Mockito.times(1)).create(
                argThat(category -> Objects.equals(expectedCategory.getName(), category.getName())
                        && Objects.equals(expectedCategory.getDescription(), category.getDescription())
                        && Objects.equals(category.isActive(), true)
                        && Objects.nonNull(category.getId())
                        && Objects.nonNull(category.getCreatedAt())
                        && Objects.nonNull(category.getUpdatedAt())
                        && Objects.isNull(category.getDeletedAt())
                )
        );
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
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_thenShouldReturnException() {

        final var expectedError = "any error";
        final var expectedCategory = Category.newCategory("any name", "any description", true);

        when(repository.create(any()))
                .thenThrow(new IllegalArgumentException(expectedError));

        final var notification = sut.execute(CreateCategoryInputDto.with(
                expectedCategory.getName(),
                expectedCategory.getDescription())).getLeft();

        Assertions.assertEquals(notification.getErrors().size(), 1);
        Assertions.assertEquals(notification.firstError().message(), expectedError);
    }
}
