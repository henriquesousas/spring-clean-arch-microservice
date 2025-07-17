package com.opinai.product.infrastructure.subcategory;

import com.opinai.product.application.subcategory.create.CreateSubcategoryCommand;
import com.opinai.product.application.subcategory.create.CreateSubcategoryUseCase;
import com.opinai.product.application.subcategory.retrieve.GetSubcategoryCommand;
import com.opinai.product.application.subcategory.retrieve.GetSubcategoryByIdUseCase;
import com.opinai.product.infrastructure.subcategory.models.CreateSubcategoryResponse;
import com.opinai.product.infrastructure.subcategory.models.SubcategoryResponse;
import com.opinai.product.infrastructure.subcategory.models.CreateSubcategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class SubcategoryController implements HttpSubcategoryController {

    @Autowired
    private CreateSubcategoryUseCase createSubcategoryUseCase;

    @Autowired
    private GetSubcategoryByIdUseCase getSubcategoryByIdUseCase;

    @Override
    public ResponseEntity<CreateSubcategoryResponse> create(CreateSubcategoryRequest request) {

        final var command = CreateSubcategoryCommand.with(
                request.name(),
                request.categories()
        );
        return this.createSubcategoryUseCase.execute(command)
                .map(data -> {
                    final var response = CreateSubcategoryResponse.with(data.getId().getValue());
                    final var location = URI.create("/genres/" + data.getId());
                    return ResponseEntity.created(location).body(response);
                })
                .getOrElseThrow(error -> error);
    }

    @Override
    public ResponseEntity<SubcategoryResponse> getById(String id) {
        final var command = new GetSubcategoryCommand(id);
        return getSubcategoryByIdUseCase.execute(command)
                .map(data -> ResponseEntity.ok(SubcategoryResponse.with(data))
                )
                .getOrElseThrow(error -> error);
    }
}
