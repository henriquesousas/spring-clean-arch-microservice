package com.jobee.admin.service.infrastructure.genre;

import com.jobee.admin.service.infrastructure.genre.models.GenreJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreJpaRepository  extends JpaRepository<GenreJpaEntity, String> {
}
