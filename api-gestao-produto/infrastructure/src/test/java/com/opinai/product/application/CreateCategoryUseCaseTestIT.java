package com.opinai.product.application;

import com.opinai.product.IntegrationTest;
import br.com.opinai.api.gestao.produto.application.category.create.CreateCategoryCommand;
import br.com.opinai.api.gestao.produto.application.category.create.CreateCategoryUseCase;
import br.com.opinai.api.gestao.produto.domain.category.CategoryBuilder;
import br.com.opinai.api.gestao.produto.domain.category.CategoryRepository;
import br.com.opinai.api.gestao.produto.infrastructure.category.CategoryJpaRepository;
import br.com.opinai.api.gestao.produto.infrastructure.category.models.CategoryJpaEntity;
import com.opinai.shared.domain.validation.Error;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@IntegrationTest
public class CreateCategoryUseCaseTestIT {

    @Autowired
    private CreateCategoryUseCase sut;

    @Autowired
    private CategoryJpaRepository repository;

    @SpyBean
    private CategoryRepository categoryRepositoryGateway;

    @Test
    public void giveAnValidCommand_whenCallCreateCategoryUseCase_thenShouldReturnCategoryId() {

        final var expectedCategory = CategoryBuilder.newCategory("any name", "any description").build();

        Assertions.assertEquals(repository.count(), 0);

        final var command = CreateCategoryCommand.with(
                expectedCategory.getName(),
                expectedCategory.getDescription());

        final var categoryId = sut.execute(command).get();

        Assertions.assertEquals(repository.count(), 1);
        Assertions.assertNotNull(categoryId);
        Assertions.assertNotNull(categoryId.categoryId());

        CategoryJpaEntity actualCategory = repository.findById(categoryId.categoryId()).get();
        Assertions.assertEquals(actualCategory.getName(), expectedCategory.getName());
        Assertions.assertEquals(actualCategory.getDescription(), expectedCategory.getDescription());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidName_whenCallsCreateCategoryUseCase_thenShouldDomainException() {

        final var expectedErrors = List.of(
                new Error("'name' should not be null or empty"),
                new Error("'name' must be between 3 and 255 characters")
        );

        final var expectedCategory = CategoryBuilder.newCategory("", "any description").build();

        final var command = CreateCategoryCommand.with(
                expectedCategory.getName(),
                expectedCategory.getDescription());

        final var notification = sut.execute(command).getLeft();

        Assertions.assertEquals(notification.getErrors().size(), 2);
        Assertions.assertEquals(notification.getErrors(), expectedErrors);
        Assertions.assertEquals(notification.getErrors(), expectedErrors);

        Assertions.assertEquals(repository.count(), 0);

        Mockito.verify(categoryRepositoryGateway, Mockito.times(0)).create(any());
    }
}
