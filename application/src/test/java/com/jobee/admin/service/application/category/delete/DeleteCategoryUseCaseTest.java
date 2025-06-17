package com.jobee.admin.service.application.category.delete;

import com.jobee.admin.service.application.usecases.Unit;
import com.jobee.admin.service.application.usecases.category.delete.DeleteCategoryUseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryBuilder;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.validation.handler.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryUseCaseTest {

    @InjectMocks
    private DeleteCategoryUseCase sut;

    @Mock
    private CategoryRepository repository;

    @Test
    public void giveAnInvalidCategoryId_whenCallDeleteCategory_thenShouldReturnNotification() {

        final var categoryId = CategoryId.unique();
        final var expectedError = NotFoundException.with(Category.class,categoryId);

        Notification notification = this.sut.execute(categoryId.getValue()).getLeft();

        Assertions.assertTrue(notification.hasError());
        Assertions.assertEquals(notification.getErrors().size(), 1);
        Assertions.assertEquals(notification.getFirstError().message(), expectedError.getMessage());

        Mockito.verify(repository, Mockito.times(1)).findById(categoryId);
        Mockito.verify(repository, Mockito.times(0)).delete(categoryId);
    }


    @Test
    public void giveAnValidCategoryId_whenCallDeleteCategoryAndRepositoryThrowsAnError_thenShouldNotification() {

        final var expectedError = "any";
        final var categoryId = CategoryId.unique();
        final var expectedCategory = new CategoryBuilder("any name","any des").build();

        when(repository.findById(any()))
                .thenReturn(Optional.of(expectedCategory));

       doThrow(new IllegalArgumentException(expectedError))
               .when(repository)
               .delete(any());


        Notification notification = this.sut.execute(categoryId.getValue()).getLeft();

        Assertions.assertTrue(notification.hasError());
        Assertions.assertEquals(notification.getErrors().size(), 1);
        Assertions.assertEquals(notification.getFirstError().message(),expectedError);

        Mockito.verify(repository, Mockito.times(1)).findById(categoryId);
        Mockito.verify(repository, Mockito.times(1)).delete(categoryId);
    }

    @Test
    public void giveAnValidCategoryId_whenCallDeleteCategory_thenShouldSuccess() {

        final var expectedError = "any";
        final var categoryId = CategoryId.unique();
        final var expectedCategory = new CategoryBuilder("any name","any des").build();

        when(repository.findById(any()))
                .thenReturn(Optional.of(expectedCategory));

        Unit unit = this.sut.execute(categoryId.getValue()).get();

        Assertions.assertInstanceOf(Unit.class, unit);

        Mockito.verify(repository, Mockito.times(1)).findById(categoryId);
        Mockito.verify(repository, Mockito.times(1)).delete(categoryId);
    }
}
