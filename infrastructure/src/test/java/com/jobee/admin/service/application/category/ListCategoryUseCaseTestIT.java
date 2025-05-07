package com.jobee.admin.service.application.category;

import com.jobee.admin.service.IntegrationTest;
import com.jobee.admin.service.application.category.retrieve.CategoryOutput;
import com.jobee.admin.service.application.category.retrieve.ListCategoryUseCase;
import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryBuilder;
import com.jobee.admin.service.domain.category.CategorySearch;
import com.jobee.admin.service.domain.pagination.Pagination;
import com.jobee.admin.service.infrastructure.category.repository.CategoryModel;
import com.jobee.admin.service.infrastructure.category.repository.CategoryJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@IntegrationTest
public class ListCategoryUseCaseTestIT {

    @Autowired
    private ListCategoryUseCase sut;

    @Autowired
    private CategoryJpaRepository repository;


    @Test
    public void givenAValidCommand_whenCallsListCategory_thenShouldReturnCategories()  {
        Category category1 = CategoryBuilder.newCategory("Filmes", "desc").build();
        Category category2 = CategoryBuilder.newCategory("Series", "desc").build();
        Category category3 = CategoryBuilder.newCategory("Documentarios", "desc").build();

        var expectedPage = 0;
        var expectedPerPage = 1;
        final var expectedItemCount = 3;
        final var expectedTerms = "";
        final var expectedSort = "name";
        final var expectedDirection = "asc";

        List<Category> categories = List.of(category1, category2, category3);

        save(categories.toArray(new Category[0]));

        Assertions.assertEquals(3, repository.count());

        for (int i = 0; i < categories.size(); i++) {
            expectedPage = i;
            CategorySearch query = new CategorySearch(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);
            Pagination<CategoryOutput> actualResult = this.sut.execute(query);

            Assertions.assertEquals(actualResult.total(), expectedItemCount);
            Assertions.assertEquals(actualResult.currentPage(), expectedPage);
            Assertions.assertEquals(actualResult.perPage(), expectedPerPage);
        }
    }

    private void save(Category... categories) {
        repository.saveAll(
                Arrays.stream(categories)
                        .map(CategoryModel::from)
                        .toList()
        );
    }
}
