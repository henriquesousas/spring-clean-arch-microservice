package com.jobee.admin.service.application.genre;

import com.jobee.admin.service.application.genre.create.CreateGenreInputDto;
import com.jobee.admin.service.application.genre.create.CreateGenreUseCase;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategoryRepository;
import com.jobee.admin.service.domain.genre.GenreBuilder;
import com.jobee.admin.service.domain.genre.GenreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateGenreUseCaseTest {

    @InjectMocks
    private CreateGenreUseCase sut;

    @Mock
    private GenreRepository repository;

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

        final var expectedGenre = new GenreBuilder("any name", "any description", expectedCategories)
                .build();

        when(repository.create(any()))
                .thenReturn(expectedGenre);

        when(categoryRepository.existByIds(any()))
                .thenReturn(expectedCategories);

        final var dto = new CreateGenreInputDto(
                "any",
                "any",
               List.of(cat1.getValue(), cat2.getValue())
        );

        final var genre = sut.execute(dto).get();

        Assertions.assertEquals(expectedGenre.getId(), genre.getId());
        Assertions.assertEquals(expectedGenre.getName(), genre.getName());
        Assertions.assertEquals(expectedGenre.getDescription(), genre.getDescription());
        Assertions.assertEquals(expectedGenre.getCategories(), genre.getCategories());
        Assertions.assertFalse(expectedGenre.getNotification().hasError());
        Assertions.assertEquals(expectedGenre.getCategories().size(), genre.getCategories().size());
    }

    @Test
    public void  givenAListOfCategoriesWithRepeatedValues_whenCallsExecute_thenSaveOnlyTheOnesThatAreNotRepeated(){
        final var cat1 = CategoryId.unique();
        final var cat3 = "Invalid Category";

        final var expectedGenre = new GenreBuilder("any name", "any description", List.of(cat1))
                .build();

        when(repository.create(any()))
                .thenReturn(expectedGenre);

        when(categoryRepository.existByIds(any()))
                .thenReturn(List.of(cat1));

        final var dto = new CreateGenreInputDto(
                "any",
                "any",
               List.of(cat1.getValue(), cat1.getValue(), cat3)
        );

        final var genre = sut.execute(dto).get();

        Assertions.assertEquals(expectedGenre.getId(), genre.getId());
        Assertions.assertEquals(expectedGenre.getName(), genre.getName());
        Assertions.assertEquals(expectedGenre.getDescription(), genre.getDescription());
        Assertions.assertEquals(expectedGenre.getCategories(), genre.getCategories());
        Assertions.assertFalse(expectedGenre.getNotification().hasError());
        Assertions.assertEquals(expectedGenre.getCategories().size(), genre.getCategories().size());

    }
}
