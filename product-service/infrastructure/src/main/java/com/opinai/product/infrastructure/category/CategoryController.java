package com.opinai.product.infrastructure.category;

import com.opinai.product.application.category.create.CreateCategoryInputDto;
import com.opinai.product.application.category.create.CreateCategoryOutputDto;
import com.opinai.product.application.category.create.CreateCategoryUseCase;
import com.opinai.product.application.category.delete.DeleteCategoryUseCase;
import com.opinai.product.application.category.retrieve.GetCategoryByIdUseCase;
import com.opinai.product.application.category.retrieve.ListCategoryUseCase;
import com.opinai.product.application.category.update.UpdateCategoryInputDto;
import com.opinai.product.application.category.update.UpdateCategoryUseCase;
import com.opinai.product.infrastructure.category.models.CreateCategoryRequest;
import com.opinai.product.infrastructure.category.models.CategoryResponse;
import com.opinai.product.infrastructure.category.models.PaginationCategoryResponse;
import com.opinai.shared.domain.pagination.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

//TODO:  Fix responses shape
@RestController
public class CategoryController implements HttpCategoryController {

    @Autowired
    private  CreateCategoryUseCase createCategoryUseCase;

    @Autowired
    private  GetCategoryByIdUseCase getCategoryByIdUseCase;

    @Autowired
    private  UpdateCategoryUseCase updateCategoryUseCase;

    @Autowired
    private  DeleteCategoryUseCase deleteCategoryUseCase;

    @Autowired
    private  ListCategoryUseCase listCategoryUseCase;


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
