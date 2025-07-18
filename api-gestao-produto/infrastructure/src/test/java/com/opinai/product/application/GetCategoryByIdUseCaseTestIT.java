package com.opinai.product.application;

import com.opinai.product.IntegrationTest;
import br.com.opinai.api.gestao.produto.application.category.retrieve.CategoryOutput;
import br.com.opinai.api.gestao.produto.application.category.retrieve.GetCategoryByIdUseCase;
import br.com.opinai.api.gestao.produto.domain.category.Category;
import br.com.opinai.api.gestao.produto.domain.category.CategoryBuilder;
import br.com.opinai.api.gestao.produto.domain.category.CategoryId;
import br.com.opinai.api.gestao.produto.domain.category.CategoryRepository;
import br.com.opinai.api.gestao.produto.infrastructure.category.CategoryJpaRepository;
import br.com.opinai.api.gestao.produto.infrastructure.category.models.CategoryJpaEntity;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

@IntegrationTest
public class GetCategoryByIdUseCaseTestIT {

    @Autowired
    private GetCategoryByIdUseCase sut;

    @Autowired
    private CategoryJpaRepository repository;

    @SpyBean
    private CategoryRepository categoryRepositoryGateway;

    @Test
    public void givenAnInvalidCategoryId_whenCallsGetCategory_thenReturnNotFoundException() {

        final var invalidCategoryId = CategoryId.from("123");
        final var expectedError = NotFoundException.with(Category.class, invalidCategoryId);

        DomainException exception = this.sut.execute(invalidCategoryId.getValue()).getLeft();

        Assertions.assertEquals(0, repository.count());

        Assertions.assertEquals(exception.getStatus(), 404);
        Assertions.assertEquals(exception.getMessage(), expectedError.getMessage());
    }

    @Test
    public void givenAValidCategory_whenCallsFindById_thenReturnCategory() {

        Category movie = CategoryBuilder.newCategory("Filmes", "any").build();

        Assertions.assertEquals(0, repository.count());

        save(
            movie,
                CategoryBuilder.newCategory("Series", "any").build(),
                CategoryBuilder.newCategory("Documentarios", "any").build()
        );

        Assertions.assertEquals(3, repository.count());

        CategoryOutput currentCategory = this.sut.execute(movie.getId().getValue()).get();

        Assertions.assertEquals(currentCategory.id().getValue(), movie.getId().getValue());
        Assertions.assertEquals(currentCategory.name(), movie.getName());
        Assertions.assertEquals(currentCategory.description(), movie.getDescription());
        Assertions.assertTrue(currentCategory.isActive());
        Assertions.assertNotNull(currentCategory.createdAt());
        Assertions.assertNotNull(currentCategory.updatedAt());
        Assertions.assertNull(currentCategory.deletedAt());
    }

    private void save(Category... category) {
        this.repository.saveAllAndFlush(
                Arrays.stream(category)
                        .map(CategoryJpaEntity::from)
                        .toList()
        );
    }

    //    @Test
//    public void givenAValidCategoryId_whenCallsGetCategoryAndRepositoryThrowsAnError_thenReturnNotification() {
//
//        final var invalidCategoryId = CategoryId.unique();
//
//        Mockito.doThrow(NotFoundException.with(new Error("any")))
//                .when(categoryRepositoryGateway).findById(any());
//
//        DomainException exception = this.sut.execute(invalidCategoryId.getValue()).getLeft();
//
//        Assertions.assertEquals(0, repository.count());
//
//        Assertions.assertEquals(exception.getErrors().get(0).message(), "any");
//    }
}
