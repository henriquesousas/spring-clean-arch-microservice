package com.jobee.admin.service.application.category;

import com.jobee.admin.service.IntegrationTest;
import com.jobee.admin.service.application.usecases.category.retrieve.GetCategoryByIdUseCase;
import com.jobee.admin.service.application.usecases.category.retrieve.CategoryOutput;
import com.jobee.admin.service.domain.core.category.Category;
import com.jobee.admin.service.domain.core.category.CategoryBuilder;
import com.jobee.admin.service.domain.core.category.CategoryId;
import com.jobee.admin.service.domain.core.category.CategoryRepository;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.infrastructure.core.category.repository.CategoryJpaRepository;
import com.jobee.admin.service.infrastructure.core.category.repository.CategoryModel;
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
                        .map(CategoryModel::from)
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
