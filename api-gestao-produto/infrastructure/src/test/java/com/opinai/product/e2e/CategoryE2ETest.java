package com.opinai.product.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opinai.product.E2ETest;
import br.com.opinai.api.gestao.produto.domain.category.CategoryId;
import br.com.opinai.api.gestao.produto.infrastructure.category.models.CreateCategoryRequest;
import br.com.opinai.api.gestao.produto.infrastructure.category.CategoryJpaRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@E2ETest
@Testcontainers
public class CategoryE2ETest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CategoryJpaRepository categoryRepository;


    @Autowired
    private ObjectMapper objectMapper;

    @Container
    static final MySQLContainer<?> MYSQL_CONTAINER =
            new MySQLContainer<>("mysql:8.0")
                    .withPassword("admin")
                    .withUsername("root")
                    .withDatabaseName("services");

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        final var mappedPort = MYSQL_CONTAINER.getMappedPort(3306);
        System.out.printf("Container is runnig, port is %s\n", mappedPort);
        registry.add("mysql.port", () -> mappedPort);
    }


    @Test
    public void testUpContainer() {
        Assertions.assertTrue(MYSQL_CONTAINER.isRunning());
    }

    @Test
    public void asACatalogAdminIShouldBeAbleToCreateANewCategoryWithValidValues() throws Exception {
        Assertions.assertTrue(MYSQL_CONTAINER.isRunning());
        Assertions.assertEquals(0, categoryRepository.count());

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualId = givenACategory(expectedName, expectedDescription, expectedIsActive);

        final var actualCategory = categoryRepository.findById(actualId.getValue()).get();

        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.toAggregate().isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void asAServiceAdminIShouldBeAbleToGetAllCategories() throws Exception {
        Assertions.assertTrue(MYSQL_CONTAINER.isRunning());
        Assertions.assertEquals(0, categoryRepository.count());

        final var actualMovieId = givenACategory("Movie", "desc1", true);
        final var actualSeriesId = givenACategory("Series", "desc1", true);
        final var actualDocumentaryId = givenACategory("Documentarios", "desc1", true);

        // page 1
        listCategories(0, 1)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.current_page", Matchers.equalTo(0)))
                .andExpect(jsonPath("$.per_page", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.total", Matchers.equalTo(3)))
                .andExpect(jsonPath("$.items[0].id", Matchers.equalTo(actualDocumentaryId.getValue())));

        // page 2
        listCategories(1, 1)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.current_page", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.per_page", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.total", Matchers.equalTo(3)))
                .andExpect(jsonPath("$.items[0].id", Matchers.equalTo(actualMovieId.getValue())));

        // page 3
        listCategories(2, 1)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.current_page", Matchers.equalTo(2)))
                .andExpect(jsonPath("$.per_page", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.total", Matchers.equalTo(3)))
                .andExpect(jsonPath("$.items[0].id", Matchers.equalTo(actualSeriesId.getValue())));
    }
    
    @Test
    public void asAServiceAdminIShouldBeAbleToGetAllCategoriesByTerm() throws Exception {
        Assertions.assertTrue(MYSQL_CONTAINER.isRunning());
        Assertions.assertEquals(0, categoryRepository.count());

        final var actualMovieId = givenACategory("Movie", "desc1", true);
        final var actualSeriesId = givenACategory("Series", "desc1", true);
        final var actualDocumentaryId = givenACategory("Documentarios", "desc1", true);

        // page 1
        listCategories(0, 10, "Movie")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.current_page", Matchers.equalTo(0)))
                .andExpect(jsonPath("$.per_page", Matchers.equalTo(10)))
                .andExpect(jsonPath("$.total", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.items[0].id", Matchers.equalTo(actualMovieId.getValue())));

    }

    private ResultActions listCategories(final int page, final int perPage) throws Exception {
        return listCategories(page, perPage, "", "", "");
    }

    private ResultActions listCategories(final int page, final int perPage, String search) throws Exception {
        return listCategories(0, 10, search, "", "");
    }

    private ResultActions listCategories(final int page, final int perPage, final String search,
                                         final String sort, final String dir) throws Exception {
        final var aRequest = get("/categories")
                .param("page", String.valueOf(page))
                .param("perPage", String.valueOf(perPage))
                .param("search", search)
                .param("sort", sort)
                .param("dir", dir)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        return this.mvc.perform(aRequest);
    }

    private CategoryId givenACategory(final String name, final String description, final boolean isActive) throws Exception {
        final var aRequestBody = new CreateCategoryRequest(name, description, isActive);

        final var aRequest = post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aRequestBody));

        final var response = this.mvc.perform(aRequest)
                .andExpect(status().isCreated())
                .andReturn();

        //Pegar a resposta do body e nao do Location
        final var actualId = response.getResponse()
                .getHeader("Location")
                .replace("/categories/", "");

        return CategoryId.from(actualId);
    }
}
