package com.jobee.admin.service.infrastructure.category;

import org.springframework.data.jpa.domain.Specification;

// TODO: Mover para um utils
public final class SpecificationUtils {

    private SpecificationUtils() {
    }

    public static <T> Specification<T> like(final String prop, final String term) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.upper(root.get(prop)), "%" + term.toUpperCase() + "%");

    }
}
