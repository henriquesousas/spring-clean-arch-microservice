package com.jobee.admin.service.application.category.retrieve;

import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepositoryGateway;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.validation.handler.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCategoryByIdUseCaseTest {


    @InjectMocks
    private GetCategoryByIdUseCase sut;

    @Mock
    private CategoryRepositoryGateway repository;

    @Test
    public  void givenInvalidCategoryId_whenCallsGetCategory_thenReturnNotification(){

        final var invalidCategoryId = CategoryId.from("123");
        final var expectedError  = NotFoundException.with(Category.class, invalidCategoryId);

        Notification notification = this.sut.execute(invalidCategoryId.getValue()).getLeft();

        Assertions.assertTrue(notification.hasError());
        Assertions.assertEquals(notification.getErrors().size(),1);
        Assertions.assertEquals(notification.firstError().message(),expectedError.getMessage());
    }


    @Test
    public  void givenAValidCategoryId_whenCallsGetCategoryAndRepositoryThrowsAnError_thenReturnNotification(){

        final var invalidCategoryId = CategoryId.from("123");
        when(repository.findById(any()))
                .thenThrow(NotFoundException.with(new Error("any")));

        Notification notification = this.sut.execute(invalidCategoryId.getValue()).getLeft();

        Assertions.assertTrue(notification.hasError());
        Assertions.assertEquals(notification.getErrors().size(),1);
        Assertions.assertEquals(notification.firstError().message(), "any");
    }
}
