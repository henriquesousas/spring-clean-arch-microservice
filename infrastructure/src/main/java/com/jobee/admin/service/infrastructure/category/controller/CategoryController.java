package com.jobee.admin.service.infrastructure.category.controller;

import com.jobee.admin.service.application.category.cretate.CreateCategoryInputDto;
import com.jobee.admin.service.application.category.cretate.CreateCategoryOutputDto;
import com.jobee.admin.service.application.category.cretate.CreateCategoryUseCase;
import com.jobee.admin.service.application.category.delete.DeleteCategoryUseCase;
import com.jobee.admin.service.application.category.retrieve.GetCategoryByIdUseCase;
import com.jobee.admin.service.application.category.retrieve.GetCategoryOutput;
import com.jobee.admin.service.application.category.retrieve.ListCategoryUseCase;
import com.jobee.admin.service.application.category.update.UpdateCategoryInputDto;
import com.jobee.admin.service.application.category.update.UpdateCategoryUseCase;
import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.category.CategorySearch;
import com.jobee.admin.service.domain.validation.handler.Notification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;


@RestController
public class CategoryController implements BaseCategoryController {

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
    public ResponseEntity<?> create(final CreateCategoryRequestDto dto) {
        CreateCategoryInputDto command = CreateCategoryInputDto.with(dto.name(), dto.description());

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateCategoryOutputDto, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/categories/" + output.categoryId())).body(output);

        return this.createCategoryUseCase.execute(command)
                .fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> update(final @PathVariable String id, final CreateCategoryRequestDto dto) {

        final var response = this.updateCategoryUseCase.execute(new UpdateCategoryInputDto(
                id,
                dto.name(),
                dto.description()
        ));
        // TODO: Deixar mais dinamico o status code , pode retornar 404,422 UnprocessableEntity
        return response.fold(
                error -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(error),
                data -> ResponseEntity.noContent().build()
        );
    }

    @Override
    public ResponseEntity<?> getById(final @PathVariable String id) {
        return this.getCategoryByIdUseCase.execute(id)
                .fold(
                        error -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(error),
                        output -> ResponseEntity.ok().body(GetCategoryResponse.from(output))
                );
    }

    @Override
    public ResponseEntity<?> listAll(final String search, final int page, final int perPage, final String sort, final String dir) {
       final var response = this.listCategoryUseCase.execute( new CategorySearch(
                page,
                perPage,
                search,
                sort,
                dir
        ));
       return  ResponseEntity.ok(PaginationCategoryResponse.from(response));
    }

    @Override
    public ResponseEntity<?> delete(String id) {
        final var response = this.deleteCategoryUseCase.execute(CategoryId.from(id));
        return response.fold(
                error -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(error),
                data -> ResponseEntity.noContent().build()
        );
    }
}
