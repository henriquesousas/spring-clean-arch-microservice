package com.opinai.product.application.subcategory;

import com.opinai.product.application.UseCaseTest;
import com.opinai.product.application.subcategory.create.CreateSubcategoryInputDto;
import com.opinai.product.application.subcategory.create.CreateSubcategoryUseCase;
import com.opinai.product.domain.category.CategoryId;
import com.opinai.product.domain.category.CategoryRepository;
import com.opinai.product.domain.subcategory.SubcategoryBuilder;
import com.opinai.product.domain.subcategory.SubcategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateSubcategoryUseCaseTest extends UseCaseTest {

    @InjectMocks
    private CreateSubcategoryUseCase sut;

    @Mock
    private SubcategoryRepository repository;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void  testInjectDependency() {
        Assertions.assertNotNull(sut);
        Assertions.assertNotNull(repository);
        Assertions.assertNotNull(categoryRepository);
    }

    @Test
    public void  givenAValidValues_whenCallsExecute_shouldCreateANewGenre(){
        final var cat1 = CategoryId.unique();
        final var cat2 = CategoryId.unique();

        final var expectedCategories = List.of(cat1, cat2);

        final var expectedGenre = new SubcategoryBuilder("any name", expectedCategories)
                .build();

        when(repository.create(any()))
                .thenReturn(expectedGenre);

        when(categoryRepository.existByIds(any()))
                .thenReturn(expectedCategories);

        final var dto = new CreateSubcategoryInputDto(
                "any",
               List.of(cat1.getValue(), cat2.getValue())
        );

        final var genre = sut.execute(dto).get();

        Assertions.assertEquals(expectedGenre.getId(), genre.getId());
        Assertions.assertEquals(expectedGenre.getName(), genre.getName());
        Assertions.assertEquals(expectedGenre.getCategories(), genre.getCategories());
        Assertions.assertFalse(expectedGenre.getNotification().hasError());
        Assertions.assertEquals(expectedGenre.getCategories().size(), genre.getCategories().size());
    }

    @Test
    public void  givenAListOfCategoriesWithRepeatedValues_whenCallsExecute_thenSaveOnlyTheOnesThatAreNotRepeated(){
        final var cat1 = CategoryId.unique();
        final var cat3 = "Invalid Category";

        final var expectedGenre = new SubcategoryBuilder("any name",  List.of(cat1))
                .build();

        when(repository.create(any()))
                .thenReturn(expectedGenre);

        when(categoryRepository.existByIds(any()))
                .thenReturn(List.of(cat1));

        final var dto = new CreateSubcategoryInputDto(
                "any",
               List.of(cat1.getValue(), cat1.getValue(), cat3)
        );

        final var genre = sut.execute(dto).get();

        Assertions.assertEquals(expectedGenre.getId(), genre.getId());
        Assertions.assertEquals(expectedGenre.getName(), genre.getName());
        Assertions.assertEquals(expectedGenre.getCategories(), genre.getCategories());
        Assertions.assertFalse(expectedGenre.getNotification().hasError());
        Assertions.assertEquals(expectedGenre.getCategories().size(), genre.getCategories().size());

    }

    @Override
    protected List<Object> getMocks() {
        return List.of(repository);
    }
}
