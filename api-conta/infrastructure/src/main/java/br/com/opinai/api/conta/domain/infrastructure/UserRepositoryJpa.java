package br.com.opinai.api.conta.domain.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJpa extends JpaRepository<UserJpaEntity, String> {
}
