package com.jobee.admin.service.application.category;

import com.jobee.admin.service.IntegrationTest;
import com.jobee.admin.service.application.category.update.UpdateCategoryInputDto;
import com.jobee.admin.service.application.category.update.UpdateCategoryOutputDto;
import com.jobee.admin.service.application.category.update.UpdateCategoryUseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryRepositoryGateway;
import com.jobee.admin.service.infrastructure.category.repository.CategoryModel;
import com.jobee.admin.service.infrastructure.category.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@IntegrationTest
public class UpdateCategoryUseCaseIT {

    @Autowired
    private UpdateCategoryUseCase sut;

    @Autowired
    private CategoryRepository repository;

    @SpyBean
    private CategoryRepositoryGateway categoryRepositoryGateway;


    @Test
    public void giveAnInvalidCommand_whenCallsUpdateCategory_thenShouldReturnCategory() {
        final var expectedName = "UpdatedName";
        final var expectedDescription = "UpdatedDesc";
        final var expectedCategory = Category.newCategory("any name", "description", true);
        final var expectedUpdatedAt = expectedCategory.getUpdatedAt();

        Assertions.assertEquals(0, repository.count());

        //Save a new category first
        this.repository.save(CategoryModel.from(expectedCategory));

        Assertions.assertEquals(1, repository.count());

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
