package br.com.opinai.api.conta.domain.infrastructure.configuration;

import br.com.opinai.api.conta.application.cryptography.Encrypt;
import br.com.opinai.api.conta.application.usecase.create.CreateUserUseCase;
import br.com.opinai.api.conta.application.usecase.getByEmail.GetUserByIdUseCase;
import br.com.opinai.api.conta.application.usecase.update.UpdateUserUseCase;
import br.com.opinai.api.conta.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class UseCaseConfig {

    private final UserRepository repository;
    private final Encrypt encrypt;

    public UseCaseConfig(final UserRepository repository, final Encrypt encrypt) {
        this.repository = Objects.requireNonNull(repository);
        this.encrypt = Objects.requireNonNull(encrypt);
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserUseCase(repository, encrypt);
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
