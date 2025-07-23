package br.com.opinai.api.conta.domain.infrastructure;

import br.com.opinai.api.conta.application.usecase.create.CreateUserUseCase;
import br.com.opinai.api.conta.application.usecase.getByEmail.GetUserByIdUseCase;
import br.com.opinai.api.conta.application.usecase.update.UpdateUserUseCase;
import br.com.opinai.api.conta.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class UserBean {

    private final UserRepository repository;

    public UserBean(final UserRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserUseCase(repository);
    }

    @Bean
    public GetUserByIdUseCase getUserByIdUseCase() {
        return new GetUserByIdUseCase(repository);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase() {
        return new UpdateUserUseCase(repository);
    }
}
