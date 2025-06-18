package com.jobee.admin.service.infrastructure.category.controller;

import com.jobee.admin.service.application.usecases.category.create.CreateCategoryInputDto;
import com.jobee.admin.service.application.usecases.category.create.CreateCategoryOutputDto;
import com.jobee.admin.service.application.usecases.category.create.CreateCategoryUseCase;
import com.jobee.admin.service.application.usecases.category.delete.DeleteCategoryUseCase;
import com.jobee.admin.service.application.usecases.category.retrieve.GetCategoryByIdUseCase;
import com.jobee.admin.service.application.usecases.category.retrieve.ListCategoryUseCase;
import com.jobee.admin.service.application.usecases.category.update.UpdateCategoryInputDto;
import com.jobee.admin.service.application.usecases.category.update.UpdateCategoryUseCase;
import com.jobee.admin.service.domain.pagination.Search;
import com.jobee.admin.service.infrastructure.category.models.CreateCategoryRequest;
import com.jobee.admin.service.infrastructure.category.models.CategoryResponse;
import com.jobee.admin.service.infrastructure.category.models.PaginationCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;


@RestController
public class CategoryController implements HttpCategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final ListCategoryUseCase listCategoryUseCase;

    public CategoryController(
            CreateCategoryUseCase createCategoryUseCase,
            GetCategoryByIdUseCase getCategoryByIdUseCase,
            UpdateCategoryUseCase updateCategoryUseCase,
            DeleteCategoryUseCase deleteCategoryUseCase,
            ListCategoryUseCase listCategoryUseCase
    ) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
        this.getCategoryByIdUseCase = Objects.requireNonNull(getCategoryByIdUseCase);
        this.updateCategoryUseCase = Objects.requireNonNull(updateCategoryUseCase);
        this.deleteCategoryUseCase = Objects.requireNonNull(deleteCategoryUseCase);
        this.listCategoryUseCase = Objects.requireNonNull(listCategoryUseCase);
    }

    @Override
    public ResponseEntity<CreateCategoryOutputDto> create(final CreateCategoryRequest dto) {
        CreateCategoryInputDto command = CreateCategoryInputDto.with(dto.name(), dto.description());

        return this.createCategoryUseCase.execute(command)
                .map(data -> ResponseEntity.created(URI.create("/categories/" + data.categoryId())).body(data))
                .getOrElseThrow(error -> error);
    }

    @Override
    public ResponseEntity<Void> update(final @PathVariable String id, final CreateCategoryRequest dto) {
        final var response = this.updateCategoryUseCase.execute(new UpdateCategoryInputDto(
                id,
                dto.name(),
                dto.description()
        ));
        ResponseEntity<Void> noContent = ResponseEntity.noContent().build();
        return response.map(data -> noContent)
                .getOrElseThrow(error -> error);
    }

    @Override
    public ResponseEntity<CategoryResponse> getById(final @PathVariable String id) {
        return this.getCategoryByIdUseCase.execute(id)
                .map(CategoryResponse::from)
                .map(ResponseEntity::ok)
                .getOrElseThrow(error -> error);
    }

    @Override
    public ResponseEntity<?> listAll(final String search, final int page, final int perPage, final String sort, final String dir) {
        final var response = this.listCategoryUseCase.execute(new Search(
                page,
                perPage,
                search,
                sort,
                dir
        ));
        return ResponseEntity.ok(PaginationCategoryResponse.from(response));
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable String id) {
        final var response = this.deleteCategoryUseCase.execute(id);
        return response.fold(
                error -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(error),
                data -> ResponseEntity.noContent().build()
        );
    }
}
