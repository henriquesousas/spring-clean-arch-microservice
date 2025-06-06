package com.jobee.admin.service.infrastructure.genre;

import com.jobee.admin.service.MysqlRepositoryTest;
import com.jobee.admin.service.domain.core.category.CategoryBuilder;
import com.jobee.admin.service.domain.core.genre.GenreBuilder;
import com.jobee.admin.service.domain.core.genre.GenreId;
import com.jobee.admin.service.infrastructure.core.category.repository.CategoryJpaRepository;
import com.jobee.admin.service.infrastructure.core.category.repository.CategoryModel;
import com.jobee.admin.service.infrastructure.core.genre.repository.GenreJpaRepository;
import com.jobee.admin.service.infrastructure.core.genre.repository.GenreRepositoryMysql;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MysqlRepositoryTest
public class GenreRepositoryMysqlTest {

    @Autowired
    private GenreRepositoryMysql genreRepositoryMysql;

    @Autowired
    private GenreJpaRepository genreJpaRepository;

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;

    @Test
    public void testInjectDependencies() {
        Assertions.assertNotNull(genreRepositoryMysql);
        Assertions.assertNotNull(genreJpaRepository);
        Assertions.assertNotNull(categoryJpaRepository);
    }

    @Test
    public void givenAValidGenre_whenCallCreate_shouldReturnANewGenre() {
        // given
        final var expectedName = "Action";
        final var expectedCategory = CategoryBuilder.newCategory("name", "desc").build();
        final var categoryIds = List.of(expectedCategory.getId());
        final var expectedGenre = new GenreBuilder(expectedName, "desc", categoryIds).build();

        Assertions.assertEquals(this.categoryJpaRepository.count(), 0);
        this.categoryJpaRepository.save(CategoryModel.from(expectedCategory));
        Assertions.assertEquals(this.categoryJpaRepository.count(), 1);

        // when
        final var genre = this.genreRepositoryMysql.create(expectedGenre);

        // then
        Assertions.assertEquals(this.genreJpaRepository.count(), 1);
        Assertions.assertEquals(genre.getName(), expectedName);
        Assertions.assertEquals(genre.getDescription(), "desc");
        Assertions.assertEquals(genre.getCategories(), categoryIds);
        Assertions.assertEquals(genre.getCreatedAt(), expectedGenre.getCreatedAt());
        Assertions.assertEquals(genre.getUpdatedAt(), expectedGenre.getUpdatedAt());
        Assertions.assertNull(genre.getDeletedAt());
    }

    @Test
    public void givenAValidGenreId_whenFinById_shouldReturnAGenre() {
        // given
        final var expectedName = "Action";
        final var expectedDescription = "Action description";
        final var expectedCategory = CategoryBuilder.newCategory("name", expectedDescription).build();
        final var expectedCategoryIds = List.of(expectedCategory.getId());
        final var expectedGenre = new GenreBuilder(expectedName, expectedDescription, expectedCategoryIds).build();

        Assertions.assertEquals(this.categoryJpaRepository.count(), 0);
        this.categoryJpaRepository.save(CategoryModel.from(expectedCategory));
        Assertions.assertEquals(this.categoryJpaRepository.count(), 1);

        // when
        this.genreRepositoryMysql.create(expectedGenre);
        final var actualGenre = this.genreRepositoryMysql.findById(expectedGenre.getId()).get();

        Assertions.assertEquals(actualGenre.getName(), expectedName);
        Assertions.assertEquals(actualGenre.getDescription(), expectedDescription);
        Assertions.assertEquals(actualGenre.getCategories(), expectedCategoryIds);

    }

    @Test
    public void givenANotStorageGenre_whenCallFinById_shouldReturnEmptyList() {
        final var actualGenre = this.genreRepositoryMysql.findById(GenreId.unique());
        Assertions.assertTrue(actualGenre.isEmpty());

    }
}
