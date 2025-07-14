package com.opinai.product.infrastructure.genre.repository;

import com.opinai.product.domain.genre.Genre;
import com.opinai.product.domain.genre.GenreId;
import com.opinai.product.domain.genre.GenreRepository;
import com.opinai.product.infrastructure.SpecificationUtils;
import com.opinai.shared.domain.pagination.Pagination;
import com.opinai.shared.domain.pagination.Search;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class GenreRepositoryMysql implements GenreRepository {

    private final GenreJpaRepository repository;

    public GenreRepositoryMysql(final GenreJpaRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Genre create(final Genre genre) {
        final var genreJpaEntity = this.repository.save(GenreJpaEntity.from(genre));
        return genreJpaEntity.toAggregate();
    }

    @Override
    public Genre update(Genre genre) {
        final var genreJpaEntity = this.repository.save(GenreJpaEntity.from(genre));
        return genreJpaEntity.toAggregate();
    }

    @Override
    public Optional<Genre> findById(final GenreId genreId) {
        return this.repository.findById(genreId.getValue())
                .map(GenreJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Genre> findAll(Search query) {
        final var page = PageRequest.of(
                query.page(),
                query.perPage(),
                Sort.by(Sort.Direction.fromString(query.directions()), query.sort())
        );

        final var specifications = Optional.ofNullable(query.terms())
                .filter(str -> !str.isBlank())
                .map(terms -> SpecificationUtils.<GenreJpaEntity>like("name", terms))
                .orElse(null);

        final var pageResult = this.repository.findAll(where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(GenreJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public void delete(final GenreId genreId) {
        final var id = genreId.getValue();
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        }
    }

    @Override
    public List<GenreId> existsByIds(final Iterable<GenreId> genreIDS) {
        final var ids = StreamSupport.stream(genreIDS.spliterator(), false)
                .map(GenreId::getValue)
                .toList();

        return this.repository.existsByIds(ids).stream()
                .map(GenreId::from)
                .toList();
    }
}
