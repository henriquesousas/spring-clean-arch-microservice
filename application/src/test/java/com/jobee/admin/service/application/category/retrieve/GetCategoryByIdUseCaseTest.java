package com.jobee.admin.service.application.category.retrieve;

import com.jobee.admin.service.application.UseCaseTest;
import com.jobee.admin.service.application.usecases.category.retrieve.GetCategoryByIdUseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


public class GetCategoryByIdUseCaseTest extends UseCaseTest {


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

    @Override
    protected List<Object> getMocks() {
        return List.of(repository);
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
