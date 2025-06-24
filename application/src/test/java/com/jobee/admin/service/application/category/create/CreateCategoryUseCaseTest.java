package com.jobee.admin.service.application.category.create;

import com.jobee.admin.service.application.usecases.category.create.CreateCategoryInputDto;
import com.jobee.admin.service.application.usecases.category.create.CreateCategoryUseCase;
import com.jobee.admin.service.domain.category.CategoryBuilder;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.validation.Error;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @InjectMocks
    private CreateCategoryUseCase sut;

    @Mock
    private CategoryRepository repository;

    @BeforeEach
    public void cleanup() {
        Mockito.reset(repository);
    }


    @Test
    public void giveAnValidCommand_whenCallCreateCategoryUseCase_thenShouldReturnCategoryId() {

        final var expectedCategory = new CategoryBuilder("any name", "any description").build();

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
    public void givenAnInvalidName_whenCallsCreateCategoryUseCase_thenShouldReturnNotificationError() {

        final var expectedError1 = new Error("'name' should not be null or empty");
        final var expectedError2 = new Error("'name' must be between 3 and 255 characters");
        final var expectedErrors = List.of(expectedError1, expectedError2);
        final var expectedErrorMessage = "UnprocessableEntity";
        final var expectedCategory = new CategoryBuilder("", "any description").build();

        DomainException exception = sut.execute(CreateCategoryInputDto.with(
                expectedCategory.getName(),
                expectedCategory.getDescription())).getLeft();
                                                                                                                                                                                                                                                                                                                                                                                                                                 Assertions.assertEquals(exception.getErrors().size(), 2);
        Assertions.assertEquals(exception.getMessage(), expectedErrorMessage);
        Assertions.assertEquals(exception.getErrors(), expectedErrors);
    }

//    @Test
//    public void givenAValidCommand_whenGatewayThrowsRandomException_thenShouldReturnException() {
//
//        final var expectedError = "any error";
//        final var expectedCategory = new CategoryBuilder("any name", "any description").build();
//
//        when(repository.create(any()))
//                .thenThrow(new IllegalArgumentException(expectedError));
//
//        DomainException exception = sut.execute(CreateCategoryInputDto.with(
//                expectedCategory.getName(),
//                expectedCategory.getDescription())).getLeft();
//
//        Assertions.assertEquals(exception.getErrors().size(), 1);
//        Assertions.assertEquals(exception.getMessage(), expectedError);
//    }
}
