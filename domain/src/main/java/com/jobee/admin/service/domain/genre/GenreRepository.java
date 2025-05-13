package com.jobee.admin.service.domain.genre;

import com.jobee.admin.service.domain.shared.pagination.Pagination;
import com.jobee.admin.service.domain.shared.pagination.Search;

import java.util.Optional;

public interface GenreRepository {
    Genre create(Genre genre);

    Genre update(Genre genre);

    Optional<Genre> findById(GenreId id);

    Pagination<Genre> findAll(Search query);

    void delete(GenreId id);
}
