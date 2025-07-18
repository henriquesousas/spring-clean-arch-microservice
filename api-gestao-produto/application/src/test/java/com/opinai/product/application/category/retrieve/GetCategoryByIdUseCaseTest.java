package com.opinai.product.application.category.retrieve;

import br.com.opinai.api.gestao.produto.application.category.retrieve.GetCategoryByIdUseCase;
import com.opinai.product.application.UseCaseTest;
import br.com.opinai.api.gestao.produto.domain.category.Category;
import br.com.opinai.api.gestao.produto.domain.category.CategoryId;
import br.com.opinai.api.gestao.produto.domain.category.CategoryRepository;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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
