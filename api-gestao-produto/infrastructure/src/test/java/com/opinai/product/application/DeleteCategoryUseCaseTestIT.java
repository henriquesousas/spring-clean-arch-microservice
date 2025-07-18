package com.opinai.product.application;

import com.opinai.product.IntegrationTest;
import br.com.opinai.api.gestao.produto.application.category.delete.DeleteCategoryUseCase;
import br.com.opinai.api.gestao.produto.domain.category.Category;
import br.com.opinai.api.gestao.produto.domain.category.CategoryBuilder;
import br.com.opinai.api.gestao.produto.domain.category.CategoryId;
import br.com.opinai.api.gestao.produto.domain.category.CategoryRepository;
import br.com.opinai.api.gestao.produto.infrastructure.category.CategoryJpaRepository;
import br.com.opinai.api.gestao.produto.infrastructure.category.models.CategoryJpaEntity;
import com.opinai.shared.application.Unit;
import com.opinai.shared.domain.validation.handler.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@IntegrationTest
public class DeleteCategoryUseCaseTestIT {

    @Autowired
    private DeleteCategoryUseCase sut;

    @Autowired
    private CategoryJpaRepository repository;

    @SpyBean
    private CategoryRepository categoryRepositoryGateway;

    @Test
    public void givenAnInvalidCategoryId_whenCallDeleteCategory_thenShouldReturnNotification() {

        final var categoryId = CategoryId.unique();
        final var expectedError = "Category with ID %s was not found".formatted(categoryId.getValue());

        Notification notification = this.sut.execute(categoryId.getValue()).getLeft();

        Assertions.assertEquals(0, repository.count());

        Assertions.assertTrue(notification.hasError());
        Assertions.assertEquals(notification.getErrors().size(), 1);
        Assertions.assertEquals(notification.getFirstError().message(), expectedError);

        Mockito.verify(categoryRepositoryGateway, times(1)).findById(any());
        Mockito.verify(categoryRepositoryGateway, times(0)).delete(any());
    }

    @Test
    public void givenAnValidCategoryId_whenCallDeleteCategory_thenShouldSuccess() {

        Category category = CategoryBuilder.newCategory("any name", "any des").build();

        this.repository.save(CategoryJpaEntity.from(category));

        Assertions.assertEquals(1, repository.count());

        Unit unit = this.sut.execute(category.getId().getValue()).get();

        Assertions.assertEquals(0, repository.count());
        Assertions.assertInstanceOf(Unit.class, unit);
    }
}
