package com.opinai.product.infrastructure.core;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtils {

    private SpecificationUtils() {
    }

    public static <T> Specification<T> like(final String prop, final String term) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.upper(root.get(prop)), "%" + term.toUpperCase() + "%");

    }
}
