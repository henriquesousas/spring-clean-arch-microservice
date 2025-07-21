<<<<<<<< HEAD:product-service/infrastructure/src/test/java/com/opinai/product/infrastructure/GenreRepositoryMysqlTest.java
package com.opinai.product.infrastructure;

import com.opinai.product.MysqlRepositoryTest;
import com.opinai.product.domain.category.CategoryBuilder;
import com.opinai.product.domain.genre.GenreBuilder;
import com.opinai.product.domain.genre.GenreId;
import com.opinai.product.infrastructure.category.repository.CategoryJpaRepository;
import com.opinai.product.infrastructure.category.repository.CategoryModel;
import com.opinai.product.infrastructure.genre.repository.GenreJpaRepository;
import com.opinai.product.infrastructure.genre.repository.GenreRepositoryMysql;
========
package br.com.opinai.api.gestao.produto.infrastructure;

import com.opinai.product.MysqlRepositoryTest;
import br.com.opinai.api.gestao.produto.domain.category.CategoryBuilder;
import br.com.opinai.api.gestao.produto.domain.subcategory.SubcategoryBuilder;
import br.com.opinai.api.gestao.produto.domain.subcategory.SubcategoryId;
import br.com.opinai.api.gestao.produto.infrastructure.category.CategoryJpaRepository;
import br.com.opinai.api.gestao.produto.infrastructure.category.models.CategoryJpaEntity;
import br.com.opinai.api.gestao.produto.infrastructure.subcategory.SubcategoryJpaRepository;
import br.com.opinai.api.gestao.produto.infrastructure.subcategory.SubcategoryRepositoryMysql;
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/test/java/br/com/opinai/api/gestao/produto/infrastructure/SubcategoryRepositoryMysqlTest.java
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MysqlRepositoryTest
public class SubcategoryRepositoryMysqlTest {

    @Autowired
    private SubcategoryRepositoryMysql subcategoryRepositoryMysql;

    @Autowired
    private SubcategoryJpaRepository subcategoryJpaRepository;

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;

    @Test
    public void testInjectDependencies() {
        Assertions.assertNotNull(subcategoryRepositoryMysql);
        Assertions.assertNotNull(subcategoryJpaRepository);
        Assertions.assertNotNull(categoryJpaRepository);
    }

    @Test
    public void givenAValidGenre_whenCallCreate_shouldReturnANewGenre() {
        // given
        final var expectedName = "Action";
        final var expectedCategory = CategoryBuilder.newCategory("name").build();
        final var categoryIds = List.of(expectedCategory.getId());
        final var expectedGenre = new SubcategoryBuilder(expectedName,  categoryIds).build();

        Assertions.assertEquals(this.categoryJpaRepository.count(), 0);
        this.categoryJpaRepository.save(CategoryJpaEntity.from(expectedCategory));
        Assertions.assertEquals(this.categoryJpaRepository.count(), 1);

        // when
        final var genre = this.subcategoryRepositoryMysql.create(expectedGenre);

        // then
        Assertions.assertEquals(this.subcategoryJpaRepository.count(), 1);
        Assertions.assertEquals(genre.getName(), expectedName);
        Assertions.assertEquals(genre.getCategories(), categoryIds);
        Assertions.assertEquals(genre.getCreatedAt(), expectedGenre.getCreatedAt());
        Assertions.assertEquals(genre.getUpdatedAt(), expectedGenre.getUpdatedAt());
        Assertions.assertNull(genre.getDeletedAt());
    }

    @Test
    public void givenAValidGenreId_whenFinById_shouldReturnAGenre() {
        // given
        final var expectedName = "Action";
        final var expectedCategory = CategoryBuilder.newCategory("name").build();
        final var expectedCategoryIds = List.of(expectedCategory.getId());
        final var expectedGenre = new SubcategoryBuilder(expectedName, expectedCategoryIds).build();

        Assertions.assertEquals(this.categoryJpaRepository.count(), 0);
        this.categoryJpaRepository.save(CategoryJpaEntity.from(expectedCategory));
        Assertions.assertEquals(this.categoryJpaRepository.count(), 1);

        // when
        this.subcategoryRepositoryMysql.create(expectedGenre);
        final var actualGenre = this.subcategoryRepositoryMysql.findById(expectedGenre.getId()).get();

        Assertions.assertEquals(actualGenre.getName(), expectedName);
        Assertions.assertEquals(actualGenre.getCategories(), expectedCategoryIds);

    }

    @Test
    public void givenANotStorageGenre_whenCallFinById_shouldReturnEmptyList() {
        final var actualGenre = this.subcategoryRepositoryMysql.findById(SubcategoryId.unique());
        Assertions.assertTrue(actualGenre.isEmpty());

    }
}
