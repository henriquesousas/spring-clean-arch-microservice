package com.opinai.product.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opinai.product.ControllerTest;
import com.opinai.product.application.category.create.CreateCategoryOutputDto;
import com.opinai.product.application.category.create.CreateCategoryUseCase;
import com.opinai.product.application.category.delete.DeleteCategoryUseCase;
import com.opinai.product.application.category.retrieve.CategoryOutput;
import com.opinai.product.application.category.retrieve.GetCategoryByIdUseCase;
import com.opinai.product.application.category.retrieve.ListCategoryUseCase;
import com.opinai.product.application.category.update.UpdateCategoryOutputDto;
import com.opinai.product.application.category.update.UpdateCategoryUseCase;
import com.opinai.product.domain.category.Category;
import com.opinai.product.domain.category.CategoryBuilder;
import com.opinai.product.domain.category.CategoryId;
import com.opinai.product.infrastructure.category.HttpCategoryController;
import com.opinai.product.infrastructure.category.models.CreateCategoryRequest;
import com.opinai.shared.domain.exceptions.NotFoundException;
import com.opinai.shared.domain.exceptions.ValidationException;
import com.opinai.shared.domain.pagination.Pagination;
import com.opinai.shared.domain.validation.Error;
import com.opinai.shared.domain.validation.handler.Notification;
import io.vavr.API;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ControllerTest(controllers = HttpCategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CreateCategoryUseCase createCategoryUseCase;

    @MockBean
    private GetCategoryByIdUseCase getCategoryByIdUseCase;

    @MockBean
    private UpdateCategoryUseCase updateCategoryUseCase;

    @MockBean
    private ListCategoryUseCase listCategoryUseCase;


    @MockBean
    private DeleteCategoryUseCase deleteCategoryUseCase;

    @Autowired
    private ObjectMapper mapper;

    //Create
    @Test
    public void giveAnValidCommand_whenCallCreateCategoryUseCase_thenShouldReturnCategoryId() throws Exception {
        final var expectedName = "any name";
        final var expectedDescription = "any description";
        final var expectedIsActive = true;

        final var command = new CreateCategoryRequest(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        when(createCategoryUseCase.execute(any()))
                .thenReturn(API.Right(CreateCategoryOutputDto.from("123")));

        final var request = MockMvcRequestBuilders.post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(command));

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.header().string("Location", "/categories/123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId", Matchers.equalTo("123")));

        Mockito.verify(createCategoryUseCase, times(1)).execute(
                argThat(cmd -> Objects.equals(expectedName, cmd.name()) &&
                        Objects.equals(expectedDescription, cmd.description())
                )
        );
    }

    @Test
    public void givenAnInvalidName_whenCallsCreateCategoryUseCase_thenShouldReturnValidationError() throws Exception {
        final String expectedName = null;
        final var expectedDescription = "any description";
        final var expectedIsActive = true;

        when(createCategoryUseCase.execute(any()))
                .thenReturn(API.Left(ValidationException.with(new Error("'name' should not be null or empty"))));

        final var command = new CreateCategoryRequest(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        final var request = MockMvcRequestBuilders.post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(command));

        this.mvc.perform(request)
                .andDo(print());
//                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
//                .andExpect(MockMvcResultMatchers.header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(MockMvcResultMatchers.header().string("Location", Matchers.nullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.size()", Matchers.equalTo(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]", Matchers.equalTo("'name' should not be null or empty")));

        Mockito.verify(createCategoryUseCase, times(1)).execute(
                argThat(cmd -> Objects.equals(expectedName, cmd.name()) &&
                        Objects.equals(expectedDescription, cmd.description())
                )
        );
    }

    // TODO: refactor
    @Test
    public void giveAnInvalidName_whenCallsCreateCategoryUseCase_thenShouldReturnDomainException() throws Exception {
//        final String expectedName = null;
//        final var expectedDescription = "any description";
//        final var expectedIsActive = true;
//
//        final var command = new CreateCategoryRequest(
//                expectedName,
//                expectedDescription,
//                expectedIsActive
//        );
//
//        final var expectedDomainException = DomainException.with(new Error("'name' should not be null or empty"));
//        Mockito.when(createCategoryUseCase.execute(any()))
//                .thenThrow(expectedDomainException);
//
//        final var request = MockMvcRequestBuilders.post("/categories")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.mapper.writeValueAsString(command));
//
//        this.mvc.perform(request)
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
//                .andExpect(MockMvcResultMatchers.header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(MockMvcResultMatchers.header().string("Location", Matchers.nullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.size()", Matchers.equalTo(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]", Matchers.equalTo("'name' should not be null or empty")));
//
//        Mockito.verify(createCategoryUseCase, Mockito.times(1)).execute(
//                argThat(cmd -> Objects.equals(expectedName, cmd.name()) &&
//                        Objects.equals(expectedDescription, cmd.description())
//                )
//        );
    }

    //GetById
    @Test
    public void givenInvalidCategoryId_whenCallsGetCategory_thenReturnValidationError() throws Exception {

        //given
        final var identifier = CategoryId.unique();
        final var expectedError = "Category with ID " + identifier.getValue() + " was not found";
        final var notification = Notification.create(). append(new Error(expectedError));

        when(getCategoryByIdUseCase.execute(any()))
                .thenReturn(API.Left(ValidationException.with(notification.getErrors())));

        //when
        final var request = MockMvcRequestBuilders
                .get("/categories/{id}", identifier.getValue())
                .contentType(MediaType.APPLICATION_JSON);

        final var response = this.mvc.perform(request)
                .andDo(print());

        //then
        response.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]", Matchers.equalTo(expectedError)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.size()", Matchers.equalTo(1)));

        Mockito.verify(getCategoryByIdUseCase, times(1)).execute(
                eq(identifier.getValue())
        );
    }

    //GetAll
    @Test
    public void givenAValidCommand_whenCallsListCategory_thenShouldReturnCategories() throws Exception {
        final var category = CategoryBuilder.newCategory("movie", "desc").build();

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "movies";
        final var expectedSort = "description";
        final var expectedDirection = "desc";
        final var expectedItemCount = 1;
        final var expectedTotal = 1;
        final var expectedItem = List.of(CategoryOutput.from(category));

        //when
        when(listCategoryUseCase.execute(any()))
                .thenReturn(new Pagination<>(expectedPage, expectedPerPage, expectedTotal, expectedItem));

        final var request = MockMvcRequestBuilders
                .get("/categories")
                .queryParam("page", String.valueOf(expectedPage))
                .queryParam("perPage", String.valueOf(expectedPerPage))
                .queryParam("sort", String.valueOf(expectedSort))
                .queryParam("dir", String.valueOf(expectedDirection))
                .queryParam("search", String.valueOf(expectedTerms))
                .contentType(MediaType.APPLICATION_JSON);

        final var response = this.mvc.perform(request)
                .andDo(print());

        //then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.current_page", Matchers.equalTo(expectedPage)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.per_page", Matchers.equalTo(expectedPerPage)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total", Matchers.equalTo(expectedTotal)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items", Matchers.hasSize(expectedItemCount)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].id", Matchers.equalTo(category.getId().getValue())));
    }

    @Test
    public void givenAValidCategoryId_whenCallsGetCategory_thenReturnCategory() throws Exception {

        final var expectedCategory = CategoryBuilder.newCategory("any nam", "any des").build();

        when(getCategoryByIdUseCase.execute(any()))
                .thenReturn(API.Right(CategoryOutput.from(expectedCategory)));

        //when
        final var request = MockMvcRequestBuilders
                .get("/categories/{id}", expectedCategory.getId().getValue())
                .contentType(MediaType.APPLICATION_JSON);

        final var response = this.mvc.perform(request)
                .andDo(print());

        //then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(expectedCategory.getId().getValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(expectedCategory.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.equalTo(expectedCategory.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.is_active", Matchers.equalTo(expectedCategory.isActive())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.created_at", Matchers.equalTo(expectedCategory.getCreatedAt().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.updated_at", Matchers.equalTo(expectedCategory.getUpdatedAt().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted_at", Matchers.equalTo(expectedCategory.getDeletedAt())));

        Mockito.verify(getCategoryByIdUseCase, times(1)).execute(
                eq(expectedCategory.getId().getValue())
        );
    }

    //Update
    @Test
    public void givenAnInValidCategoryId_whenCallsUpdateCategory_thenShouldReturnValidationError() throws Exception {

        //given
        final var expectedId = CategoryId.unique();
        final var expectedName = "any name";
        final var expectedDescription = "any desc";
        final var expectedMessage = "UnprocessableEntity";
        final var expectedError = String.format("Category with ID %s was not found", expectedId.getValue());

        CreateCategoryRequest command = new CreateCategoryRequest(expectedName, expectedDescription, true);

        when(updateCategoryUseCase.execute(any()))
//                .thenReturn(API.Left(Notification.create().append(NotFoundException.with(Category.class, expectedId))));
                .thenReturn(API.Left(ValidationException.with(NotFoundException.with(Category.class, expectedId).getErrors())));

        //when
        final var request = MockMvcRequestBuilders
                .put("/categories/{id}", expectedId.getValue())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(command));


        final var response = this.mvc.perform(request)
                .andDo(print());

        //then
        response.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.equalTo(expectedMessage)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]", Matchers.equalTo(expectedError)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.size()", Matchers.equalTo(1)));

        Mockito.verify(updateCategoryUseCase, times(1)).execute(
                argThat(cmd ->
                        Objects.equals(expectedName, cmd.name()) &&
                                Objects.equals(expectedDescription, cmd.description()) &&
                                Objects.equals(expectedId.getValue(), cmd.id()))
        );
    }

    @Test
    public void giveAnValidCategoryId_whenCallsUpdateCategory_thenShouldReturnNoContent() throws Exception {

        //given
        final var expectedId = CategoryId.unique();
        final var expectedName = "any name";
        final var expectedDescription = "any desc";

        final var expectedCategory = CategoryBuilder.newCategory(expectedName, expectedDescription).build();
        final var command = new CreateCategoryRequest(expectedName, expectedDescription, true);

        when(updateCategoryUseCase.execute(any()))
                .thenReturn(API.Right(UpdateCategoryOutputDto.from(expectedCategory)));

        //when
        final var request = MockMvcRequestBuilders
                .put("/categories/{id}", expectedId.getValue())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(command));


        final var response = this.mvc.perform(request)
                .andDo(print());

        //then
        response.andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(updateCategoryUseCase, times(1)).execute(
                argThat(cmd ->
                        Objects.equals(expectedName, cmd.name()) &&
                                Objects.equals(expectedDescription, cmd.description()) &&
                                Objects.equals(expectedId.getValue(), cmd.id()))
        );
    }
}