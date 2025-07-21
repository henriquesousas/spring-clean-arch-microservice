package br.com.opinai.api.gestao.produto.domain.subcategory;


import com.opinai.shared.domain.pagination.Pagination;
import com.opinai.shared.domain.pagination.Search;

import java.util.List;
import java.util.Optional;

public interface SubcategoryRepository {
    Subcategory create(Subcategory subcategory);

    Subcategory update(Subcategory subcategory);

    Optional<Subcategory> findById(SubcategoryId id);

    Pagination<Subcategory> findAll(Search query);

    void delete(SubcategoryId id);

    List<SubcategoryId> existsByIds(Iterable<SubcategoryId> ids);
}
