package com.opinai.product.application;

import com.opinai.product.IntegrationTest;
<<<<<<<< HEAD:product-service/infrastructure/src/test/java/com/opinai/product/application/UpdateCategoryUseCaseTestIT.java
import com.opinai.product.application.category.update.UpdateCategoryInputDto;
import com.opinai.product.application.category.update.UpdateCategoryOutputDto;
import com.opinai.product.application.category.update.UpdateCategoryUseCase;
import com.opinai.product.domain.category.CategoryBuilder;
import com.opinai.product.domain.category.CategoryRepository;
import com.opinai.product.infrastructure.category.repository.CategoryJpaRepository;
import com.opinai.product.infrastructure.category.repository.CategoryModel;
========
import br.com.opinai.api.gestao.produto.application.category.update.UpdateCategoryCommand;
import br.com.opinai.api.gestao.produto.application.category.update.UpdateCategoryOutputDto;
import br.com.opinai.api.gestao.produto.application.category.update.UpdateCategoryUseCase;
import br.com.opinai.api.gestao.produto.domain.category.CategoryBuilder;
import br.com.opinai.api.gestao.produto.domain.category.CategoryRepository;
import br.com.opinai.api.gestao.produto.infrastructure.category.CategoryJpaRepository;
import br.com.opinai.api.gestao.produto.infrastructure.category.models.CategoryJpaEntity;
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/test/java/com/opinai/product/application/UpdateCategoryUseCaseTestIT.java
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@IntegrationTest
public class UpdateCategoryUseCaseTestIT {

    @Autowired
    private UpdateCategoryUseCase sut;

    @Autowired
    private CategoryJpaRepository repository;

    @SpyBean
    private CategoryRepository categoryRepositoryGateway;


    @Test
    public void giveAnInvalidCommand_whenCallsUpdateCategory_thenShouldReturnCategory() {
        final var expectedName = "UpdatedName";
        final var expectedDescription = "UpdatedDesc";
        final var expectedCategory = CategoryBuilder.newCategory("any name", "description").build();
        final var expectedUpdatedAt = expectedCategory.getUpdatedAt();

        assertEquals(0, repository.count());

        //Save a new category first
<<<<<<<< HEAD:product-service/infrastructure/src/test/java/com/opinai/product/application/UpdateCategoryUseCaseTestIT.java
        this.repository.save(CategoryModel.from(expectedCategory));

        assertEquals(1, repository.count());

        CategoryModel currentCategory = repository.findById(expectedCategory.getId().getValue()).get();
========
        this.repository.save(CategoryJpaEntity.from(expectedCategory));

        assertEquals(1, repository.count());

        CategoryJpaEntity currentCategory = repository.findById(expectedCategory.getId().getValue()).get();
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/test/java/com/opinai/product/application/UpdateCategoryUseCaseTestIT.java

        assertEquals(currentCategory.getId(), expectedCategory.getId().getValue());
        assertEquals(currentCategory.getName(), expectedCategory.getName());
        assertEquals(currentCategory.getDescription(), expectedCategory.getDescription());
        assertNotNull(currentCategory.getCreatedAt());
        assertNotNull(currentCategory.getUpdatedAt());
        assertNull(currentCategory.getDeletedAt());

        //Update the category
<<<<<<<< HEAD:product-service/infrastructure/src/test/java/com/opinai/product/application/UpdateCategoryUseCaseTestIT.java
        final var updateCategoryInputDto = new UpdateCategoryInputDto(
========
        final var updateCategoryInputDto = new UpdateCategoryCommand(
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/test/java/com/opinai/product/application/UpdateCategoryUseCaseTestIT.java
                expectedCategory.getId().getValue(),
                expectedName,
                expectedDescription
        );

        UpdateCategoryOutputDto updateCategoryOutputDto = this.sut.execute(updateCategoryInputDto).get();

<<<<<<<< HEAD:product-service/infrastructure/src/test/java/com/opinai/product/application/UpdateCategoryUseCaseTestIT.java
        CategoryModel categoryUpdated = repository.findById(updateCategoryOutputDto.id()).get();
========
        CategoryJpaEntity categoryUpdated = repository.findById(updateCategoryOutputDto.id()).get();
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/test/java/com/opinai/product/application/UpdateCategoryUseCaseTestIT.java

        assertEquals(categoryUpdated.getId(), expectedCategory.getId().getValue());
        assertEquals(categoryUpdated.getName(), expectedName);
        assertEquals(categoryUpdated.getDescription(), expectedDescription);
        assertNull(categoryUpdated.getDeletedAt());

        Mockito.verify(categoryRepositoryGateway, times(1)).findById(any());
        Mockito.verify(categoryRepositoryGateway, times(1)).update(any());
    }
}
