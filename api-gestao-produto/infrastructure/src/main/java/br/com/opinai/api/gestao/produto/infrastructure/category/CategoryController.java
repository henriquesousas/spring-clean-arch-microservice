package br.com.opinai.api.gestao.produto.infrastructure.category;

import br.com.opinai.api.gestao.produto.application.category.create.CreateCategoryCommand;
import br.com.opinai.api.gestao.produto.application.category.create.CreateCategoryOutput;
import br.com.opinai.api.gestao.produto.application.category.create.CreateCategoryUseCase;
import br.com.opinai.api.gestao.produto.application.category.delete.DeleteCategoryUseCase;
import br.com.opinai.api.gestao.produto.application.category.retrieve.GetCategoryByIdUseCase;
import br.com.opinai.api.gestao.produto.application.category.retrieve.ListCategoryUseCase;
import br.com.opinai.api.gestao.produto.application.category.update.UpdateCategoryCommand;
import br.com.opinai.api.gestao.produto.application.category.update.UpdateCategoryUseCase;
import br.com.opinai.api.gestao.produto.infrastructure.category.models.CreateCategoryRequest;
import br.com.opinai.api.gestao.produto.infrastructure.category.models.CategoryResponse;
import br.com.opinai.api.gestao.produto.infrastructure.category.models.PaginationCategoryResponse;
import com.opinai.shared.domain.pagination.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
public class CategoryController implements CategoryApi {

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
    public ResponseEntity<CreateCategoryOutput> create(final CreateCategoryRequest dto) {
        CreateCategoryCommand command = CreateCategoryCommand.with(dto.name());

        return this.createCategoryUseCase.execute(command)
                .map(data -> ResponseEntity.created(URI.create("/categories/" + data.categoryId())).body(data))
                .getOrElseThrow(error -> error);
    }

    @Override
    public ResponseEntity<Void> update(final @PathVariable String id, final CreateCategoryRequest dto) {
        final var response = this.updateCategoryUseCase.execute(new UpdateCategoryCommand(
                id,
                dto.name()
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
