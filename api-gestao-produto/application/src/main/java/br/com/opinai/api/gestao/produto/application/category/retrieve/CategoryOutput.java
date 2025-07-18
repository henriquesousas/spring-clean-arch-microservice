package br.com.opinai.api.gestao.produto.application.category.retrieve;


import br.com.opinai.api.gestao.produto.domain.category.Category;
import br.com.opinai.api.gestao.produto.domain.category.CategoryId;

import java.time.Instant;

public record CategoryOutput(
        CategoryId id,
        String name,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static CategoryOutput from(final Category category) {
        return new CategoryOutput(
                category.getId(),
                category.getName(),
                category.isActive(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                category.getDeletedAt()
        );
    }
}
