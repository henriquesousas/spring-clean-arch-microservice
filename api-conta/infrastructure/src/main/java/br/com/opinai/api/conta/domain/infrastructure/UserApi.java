package br.com.opinai.api.conta.domain.infrastructure;


import br.com.opinai.api.conta.domain.infrastructure.models.CreateUserRequest;
import br.com.opinai.api.conta.domain.infrastructure.models.UpdateUserRequest;
import br.com.opinai.api.conta.domain.infrastructure.models.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "users")
@Tag(name = "Users")
public interface UserApi {


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação BadRequest"),
            @ApiResponse(responseCode = "422", description = "Erro de validação"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
    })
    @Operation(summary = "Cria um novo usuário")
    ResponseEntity<UserResponse> create(@RequestBody @Valid CreateUserRequest request);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca usuário pelo seu Id")
    ResponseEntity<UserResponse> getById(@PathVariable("id") String id);

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado"),
            @ApiResponse(responseCode = "422", description = "Erro de validação"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
    })
    ResponseEntity<Void> update(
            @PathVariable(name = "id") String identifier,
            @RequestBody @Valid UpdateUserRequest request);

}
