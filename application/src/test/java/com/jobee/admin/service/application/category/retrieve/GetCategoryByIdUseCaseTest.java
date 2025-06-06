package com.jobee.admin.service.application.category.retrieve;

import com.jobee.admin.service.application.usecases.category.retrieve.GetCategoryByIdUseCase;
import com.jobee.admin.service.domain.core.category.Category;
import com.jobee.admin.service.domain.core.category.CategoryId;
import com.jobee.admin.service.domain.core.category.CategoryRepository;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class GetCategoryByIdUseCaseTest {


    @InjectMocks
    private GetCategoryByIdUseCase sut;

    @Mock
    private CategoryRepository repository;

    @Test
    public  void givenInvalidCategoryId_whenCallsGetCategory_thenReturnValidationError(){

        final var invalidCategoryId = CategoryId.from("123");
        final var expectedError  = NotFoundException.with(Category.class, invalidCategoryId);

        DomainException exception = this.sut.execute(invalidCategoryId.getValue()).getLeft();

        Assertions.assertEquals(exception.getMessage(),expectedError.getMessage());
    }


//    @Test
//    public  void givenAValidCategoryId_whenCallsGetCategoryAndRepositoryThrowsAnError_thenReturnNotification(){
//
//        final var invalidCategoryId = CategoryId.from("123");
//        when(repository.findById(any()))
//                .thenThrow(NotFoundException.with(new Error("any")));
//
//        DomainException exception = this.sut.execute(invalidCategoryId.getValue()).getLeft();
//
//        Assertions.assertEquals(exception.getMessage(), "any");
//    }
}
