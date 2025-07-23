package br.com.opinai.api.conta.domain.infrastructure;

import br.com.opinai.api.conta.domain.infrastructure.models.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<UserJpaEntity, String> {
    Optional<UserJpaEntity> findByEmail(String email);
}
