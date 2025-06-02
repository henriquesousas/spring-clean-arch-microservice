package com.jobee.admin.service.application.category.retrieve;

import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryBuilder;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.pagination.Search;
import com.jobee.admin.service.domain.pagination.Pagination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ListCategoryUseCaseTest {


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
}
