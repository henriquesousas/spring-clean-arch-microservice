package com.opinai.product.application;

import com.opinai.product.IntegrationTest;
import com.opinai.product.application.category.update.UpdateCategoryInputDto;
import com.opinai.product.application.category.update.UpdateCategoryOutputDto;
import com.opinai.product.application.category.update.UpdateCategoryUseCase;
import com.opinai.product.domain.category.CategoryBuilder;
import com.opinai.product.domain.category.CategoryRepository;
import com.opinai.product.infrastructure.category.repository.CategoryJpaRepository;
import com.opinai.product.infrastructure.category.repository.CategoryModel;
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
        this.repository.save(CategoryModel.from(expectedCategory));

        assertEquals(1, repository.count());

        CategoryModel currentCategory = repository.findById(expectedCategory.getId().getValue()).get();

        assertEquals(currentCategory.getId(), expectedCategory.getId().getValue());
        assertEquals(currentCategory.getName(), expectedCategory.getName());
        assertEquals(currentCategory.getDescription(), expectedCategory.getDescription());
        assertNotNull(currentCategory.getCreatedAt());
        assertNotNull(currentCategory.getUpdatedAt());
        assertNull(currentCategory.getDeletedAt());

        //Update the category
        final var updateCategoryInputDto = new UpdateCategoryInputDto(
                expectedCategory.getId().getValue(),
                expectedName,
                expectedDescription
        );

        UpdateCategoryOutputDto updateCategoryOutputDto = this.sut.execute(updateCategoryInputDto).get();

        CategoryModel categoryUpdated = repository.findById(updateCategoryOutputDto.id()).get();

        assertEquals(categoryUpdated.getId(), expectedCategory.getId().getValue());
        assertEquals(categoryUpdated.getName(), expectedName);
        assertEquals(categoryUpdated.getDescription(), expectedDescription);
        assertNull(categoryUpdated.getDeletedAt());

        Mockito.verify(categoryRepositoryGateway, times(1)).findById(any());
        Mockito.verify(categoryRepositoryGateway, times(1)).update(any());
    }
}
