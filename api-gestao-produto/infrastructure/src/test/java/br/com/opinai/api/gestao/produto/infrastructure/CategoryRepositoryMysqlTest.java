package br.com.opinai.api.gestao.produto.infrastructure;

import com.opinai.product.MysqlRepositoryTest;
import br.com.opinai.api.gestao.produto.domain.category.Category;
import br.com.opinai.api.gestao.produto.domain.category.CategoryBuilder;
import br.com.opinai.api.gestao.produto.domain.category.CategoryId;
import br.com.opinai.api.gestao.produto.infrastructure.category.CategoryJpaRepository;
import br.com.opinai.api.gestao.produto.infrastructure.category.models.CategoryJpaEntity;
import br.com.opinai.api.gestao.produto.infrastructure.category.CategoryRepositoryMysql;
import com.opinai.shared.domain.pagination.Search;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@MysqlRepositoryTest
public class CategoryRepositoryMysqlTest {

    @Autowired
    private CategoryRepositoryMysql categoryRepositoryMysql;


    @Autowired
    private CategoryJpaRepository repository;


    @Test
    public void testInjectDependencies() {
        Assertions.assertNotNull(categoryRepositoryMysql);
        Assertions.assertNotNull(repository);
    }

    @Test
    public void givenAValidCategory_whenCallCreate_shouldReturnANewCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "Acao";
        final var expectedIsActive = true;

        Category category = CategoryBuilder.newCategory(expectedName, expectedDescription)
                .withActive(expectedIsActive).build();

        Assertions.assertEquals(repository.count(), 0);

        Category createdCategory = categoryRepositoryMysql.create(category);

        Assertions.assertEquals(repository.count(), 1);
        Assertions.assertEquals(createdCategory.getId().getValue(), category.getId().getValue());
        Assertions.assertEquals(createdCategory.getName(), expectedName);
        Assertions.assertEquals(createdCategory.getDescription(), expectedDescription);
        Assertions.assertEquals(createdCategory.isActive(), expectedIsActive);
        Assertions.assertEquals(createdCategory.getCreatedAt(), category.getCreatedAt());
        Assertions.assertEquals(createdCategory.getUpdatedAt(), category.getUpdatedAt());
        Assertions.assertNull(createdCategory.getDeletedAt());

    }

    @Test
    public void givenAValidCategory_whenCallUpdate_shouldReturnCategoryUpdated() throws InterruptedException {
        final var expectedName = "Filme";
        final var expectedDescription = "Descricao";
        final var expectedIsActive = true;

        Category category = CategoryBuilder.newCategory("film", "desc").withActive(expectedIsActive).build();
        categoryRepositoryMysql.create(category);

        final var expectedUpdatedAt = category.getUpdatedAt();

        Thread.sleep(0, 1);

        final var newCategory = category.update(expectedName, expectedDescription);
        Category updatedCategory =
                categoryRepositoryMysql.update(newCategory);

        Assertions.assertEquals(repository.count(), 1);
        Assertions.assertEquals(updatedCategory.getId().getValue(), category.getId().getValue());
        Assertions.assertEquals(updatedCategory.getName(), expectedName);
        Assertions.assertEquals(updatedCategory.getDescription(), expectedDescription);
        Assertions.assertEquals(updatedCategory.isActive(), expectedIsActive);
        Assertions.assertEquals(updatedCategory.getCreatedAt(), category.getCreatedAt());
        Assertions.assertTrue(updatedCategory.getUpdatedAt().isAfter(expectedUpdatedAt));
        Assertions.assertNull(updatedCategory.getDeletedAt());

    }

    @Test
    public void givenAValidCategory_whenCallDelete_shouldDeleteCategory() {

        Category newCategory = CategoryBuilder .newCategory("Filme", "Descricao").build();

        categoryRepositoryMysql.create(newCategory);

        Assertions.assertEquals(repository.count(), 1);

        categoryRepositoryMysql.delete(newCategory.getId());

        Assertions.assertEquals(repository.count(), 0);
    }

    @Test
    public void givenAnInvalidCategoryId_whenCallDelete_shouldNotThrowException() {
        Assertions.assertDoesNotThrow(() -> categoryRepositoryMysql.delete(CategoryId.from("invalid_id")));
    }

    @Test
    public void givenAnCreatedCategory_whenCallFindById_shouldReturnCategory() {
        final var expectedName = "Filme";
        final var expectedDescription = "Descricao";
        final var expectedIsActive = true;

        Category newCategory = CategoryBuilder .newCategory(expectedName, expectedDescription)
                .withActive(expectedIsActive)
                .build();
        categoryRepositoryMysql.create(newCategory);

        Category category = categoryRepositoryMysql.findById(newCategory.getId()).get();

        Assertions.assertEquals(repository.count(), 1);
        Assertions.assertEquals(category.getId().getValue(), category.getId().getValue());
        Assertions.assertEquals(category.getName(), expectedName);
        Assertions.assertEquals(category.getDescription(), expectedDescription);
        Assertions.assertEquals(category.isActive(), expectedIsActive);
        Assertions.assertEquals(category.getCreatedAt(), category.getCreatedAt());
        Assertions.assertNull(category.getDeletedAt());
    }

    @Test
    public void givenAnNotStoreCategory_whenCallFindById_shouldReturnEmpty() {
        final var category = categoryRepositoryMysql.findById(CategoryId.unique());
        Assertions.assertTrue(category.isEmpty());
    }

    @Test
    public void givenAnStoreCategories_whenCallFindAll_shouldReturnCategoryPaginated() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 3;

        final var movies = CategoryBuilder.newCategory("Filmes", "any value").build();
        final var series = CategoryBuilder.newCategory("Series", "any value").build();
        final var documentaries = CategoryBuilder.newCategory("Documentarios", "any value").build();

        Assertions.assertEquals(0, repository.count());

        repository.saveAll(List.of(
                CategoryJpaEntity.from(movies),
                CategoryJpaEntity.from(series),
                CategoryJpaEntity.from(documentaries)
        ));

        Assertions.assertEquals(3, repository.count());

        final var query = new Search(0, 1, "", "name", "asc");
        final var actualResult = categoryRepositoryMysql.findAll(query);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedPerPage, actualResult.items().size());
        Assertions.assertEquals(expectedTotal, actualResult.total());
        Assertions.assertEquals(actualResult.items().get(0).getId().getValue(), documentaries.getId().getValue());
    }

    @Test
    public void givenAnEmptyDatabase_whenCallFindAll_shouldReturnEmptyPage() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 0;

        Assertions.assertEquals(0, repository.count());

        final var query = new Search(0, 1, "", "name", "asc");
        final var actualResult = categoryRepositoryMysql.findAll(query);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());
    }

    @Test
    public void givenFollowPaginated_whenCallFindAll_shouldReturnCategoryPaginated() {
        var expectedPage = 0;
        var expectedPerPage = 1;
        var expectedTotal = 3;

        final var movies = CategoryBuilder.newCategory("Filmes", "any value").build();
        final var series = CategoryBuilder.newCategory("Series", "any value").build();
        final var documentaries = CategoryBuilder.newCategory("Documentarios", "any value").build();

        Assertions.assertEquals(0, repository.count());

        repository.saveAll(List.of(
                CategoryJpaEntity.from(movies),
                CategoryJpaEntity.from(series),
                CategoryJpaEntity.from(documentaries)
        ));

        Assertions.assertEquals(3, repository.count());

        // Pagina 1
        var query = new Search(0, 1, "", "name", "asc");
        var actualResult = categoryRepositoryMysql.findAll(query);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedPerPage, actualResult.items().size());
        Assertions.assertEquals(expectedTotal, actualResult.total());
        Assertions.assertEquals(actualResult.items().get(0).getId().getValue(), documentaries.getId().getValue());

//        //Pagina 2
        expectedPage = 1;
        query = new Search(1, 1, "", "name", "asc");
        actualResult = categoryRepositoryMysql.findAll(query);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedPerPage, actualResult.items().size());
        Assertions.assertEquals(expectedTotal, actualResult.total());
        Assertions.assertEquals(actualResult.items().get(0).getId().getValue(), movies.getId().getValue());

        //Pagina 2
        expectedPage = 2;
        query = new Search(2, 1, "", "name", "asc");
        actualResult = categoryRepositoryMysql.findAll(query);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedPerPage, actualResult.items().size());
        Assertions.assertEquals(expectedTotal, actualResult.total());
        Assertions.assertEquals(actualResult.items().get(0).getId().getValue(), series.getId().getValue());
    }
}


