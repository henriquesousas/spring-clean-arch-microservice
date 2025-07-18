package com.opinai.product.application.category.retrieve;

import br.com.opinai.api.gestao.produto.application.category.retrieve.CategoryOutput;
import br.com.opinai.api.gestao.produto.application.category.retrieve.ListCategoryUseCase;
import com.opinai.product.application.UseCaseTest;
import br.com.opinai.api.gestao.produto.domain.category.Category;
import br.com.opinai.api.gestao.produto.domain.category.CategoryBuilder;
import br.com.opinai.api.gestao.produto.domain.category.CategoryRepository;
import com.opinai.shared.domain.pagination.Pagination;
import com.opinai.shared.domain.pagination.Search;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class ListCategoryUseCaseTest extends UseCaseTest {


    @InjectMocks
    private ListCategoryUseCase sut;

    @Mock
    private CategoryRepository repository;

    @BeforeEach
    public void cleanup() {
        Mockito.reset(repository);
    }

    @Test
    public void givenAValidCommand_whenCallsListCategory_thenShouldReturnCategories() throws InterruptedException {
        Category category1 = new CategoryBuilder("Vendas", "desc").build();
        Thread.sleep(0,1);
        Category category2 =  new CategoryBuilder("Marketing", "desc").build();
        var categories = List.of(category1, category2);

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";
        final var expectedItemCount = 2;

        Search query = new Search(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var expectedPagination =
                new Pagination<>(expectedPage, expectedPerPage, categories.size(), categories);


        final var expectedResult = expectedPagination.map(CategoryOutput::from);

        Mockito.when(repository.findAll(any())).thenReturn(expectedPagination);

        Pagination<CategoryOutput> actualResult = this.sut.execute(query);

        Assertions.assertEquals(actualResult.items().size(), expectedItemCount);
        Assertions.assertEquals(actualResult.currentPage(), actualResult.currentPage());
        Assertions.assertEquals(actualResult.perPage(), actualResult.perPage());
        Assertions.assertEquals(actualResult.items(), expectedResult.items());
    }

    @Override
    protected List<Object> getMocks() {
        return List.of(repository);
    }
}
