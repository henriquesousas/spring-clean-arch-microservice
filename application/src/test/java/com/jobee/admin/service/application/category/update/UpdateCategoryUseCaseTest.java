package com.jobee.admin.service.application.category.update;


import com.jobee.admin.service.application.UseCaseTest;
import com.jobee.admin.service.application.usecases.category.update.UpdateCategoryInputDto;
import com.jobee.admin.service.application.usecases.category.update.UpdateCategoryOutputDto;
import com.jobee.admin.service.application.usecases.category.update.UpdateCategoryUseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryBuilder;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.validation.Error;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class UpdateCategoryUseCaseTest  extends UseCaseTest {

    @InjectMocks
    private UpdateCategoryUseCase sut;

    @Mock
    private CategoryRepository repository;

    @Test
    public void giveAnValidCommand_whenCallsUpdateCategory_thenShouldReturnCategory() {
        final var expectedName = "UpdatedName";
        final var expectedDescription = "UpdatedDesc";
        final var expectedCategory = new CategoryBuilder ("any name", "description").build();
        final var expectedUpdatedAt = expectedCategory.getUpdatedAt();

        final var updateCategoryInputDto = new UpdateCategoryInputDto(
                expectedCategory.getId().getValue(),
                expectedName,
                expectedDescription);

        when(repository.findById(any()))
                .thenReturn(Optional.of(expectedCategory));

        when(repository.update(any()))
                .thenReturn(expectedCategory.clone());

        UpdateCategoryOutputDto outputDto = this.sut.execute(updateCategoryInputDto).get();

        assertEquals(outputDto.id(), expectedCategory.getId().getValue());
        assertEquals(expectedCategory.getName(), expectedName);
        assertEquals(expectedCategory.getDescription(), expectedDescription);
        assertNull(expectedCategory.getDeletedAt());

        Mockito.verify(repository, times(1)).findById(expectedCategory.getId());
        Mockito.verify(repository, times(1)).update(
                ArgumentMatchers.argThat(
                        categoryUpdated -> Objects.equals(categoryUpdated.getName(), expectedName)
                                && Objects.equals(categoryUpdated.getDescription(), expectedDescription)
                                && Objects.equals(categoryUpdated.isActive(), true)
                                && Objects.equals(categoryUpdated.getUpdatedAt().isAfter(expectedUpdatedAt), true)
                                && Objects.isNull(categoryUpdated.getDeletedAt())
                )
        );
    }

    @Test
    public void giveAnInvalidCategoryId_whenCallsUpdateCategory_thenShouldReturnNotification() {
        final var expectedCategoryId = "123";
        final var expectedError = "%s with ID %s was not found".formatted(
                Category.class.getSimpleName(),
                expectedCategoryId
        );

        final var updateCategoryInputDto = new UpdateCategoryInputDto(
                expectedCategoryId,
                "any",
                "description");

        when(repository.findById(any()))
                .thenReturn(Optional.empty());

        DomainException exception = this.sut.execute(updateCategoryInputDto).getLeft();

        assertEquals(exception.getMessage(),"NotFound");
        assertEquals(exception.getErrors().get(0).message() ,expectedError);
        assertEquals(exception.getStatus(), 404);

        Mockito.verify(repository, times(0)).update(any());

    }

    @Test
    public void giveAnInvalidName_whenCallsUpdateCategory_thenShouldReturnNotification() {
        final var expectedErrors = List.of(
                new Error("'name' should not be null or empty"),
                new Error("'name' must be between 3 and 255 characters"));

        final var category = new CategoryBuilder("any name", "description").build();
        final var updateCategoryInputDto = new UpdateCategoryInputDto(
                category.getId().getValue(),
                "",
                category.getDescription());

        when(repository.findById(any()))
                .thenReturn(Optional.of(category));

        DomainException exception = this.sut.execute(updateCategoryInputDto).getLeft();

        exception.getErrors().forEach(error -> {
            assertTrue(expectedErrors.contains(error));
        });


        Mockito.verify(repository, times(0)).update(any());
    }

    @Override
    protected List<Object> getMocks() {
        return List.of(repository);
    }

//    @Test
//    public void giveAnValidCommand_whenCallsUpdateCategoryAndRepositoryThrowsAnException_thenShouldReturnNotification() {
//
//        final var expectedError = "any error";
//
//        final var category = new CategoryBuilder("any name", "description").build();
//        final var updateCategoryInputDto = new UpdateCategoryInputDto(
//                category.getId().getValue(),
//                category.getName(),
//                category.getDescription());
//
//        when(repository.findById(any()))
//                .thenReturn(Optional.of(category));
//
//        when(repository.update(any()))
//                .thenThrow(new IllegalArgumentException(expectedError));
//
//        DomainException exception = this.sut.execute(updateCategoryInputDto).getLeft();
//
//        assertEquals(exception.getMessage(), expectedError);
//    }
}
