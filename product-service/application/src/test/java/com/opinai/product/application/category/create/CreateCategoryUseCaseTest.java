package com.opinai.product.application.category.create;

import com.opinai.product.application.UseCaseTest;
import com.opinai.product.domain.category.CategoryBuilder;
import com.opinai.product.domain.category.CategoryRepository;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.validation.Error;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

public class CreateCategoryUseCaseTest extends UseCaseTest {

    @InjectMocks
    private CreateCategoryUseCase sut;

    @Mock
    private CategoryRepository repository;

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

    @Override
    protected List<Object> getMocks() {
        return List.of(repository);
    }
}
