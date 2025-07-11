package com.jobee.admin.service.domain.genre;


import com.opinai.shared.domain.pagination.Pagination;
import com.opinai.shared.domain.pagination.Search;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre create(Genre genre);

    Genre update(Genre genre);

    Optional<Genre> findById(GenreId id);

    Pagination<Genre> findAll(Search query);

    void delete(GenreId id);

    List<GenreId> existsByIds(Iterable<GenreId> ids);
}
