package com.jobee.admin.service.domain.shared.pagination;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public record Pagination<T>(
        int currentPage,
        int perPage,
        long total,
        List<T> items
) {

    public <R> Pagination<R> map(final Function<T, R> mapper) {
        List<R> newList = this.items.stream()
                .map(mapper)
                .toList();

        return new Pagination<>(currentPage(), perPage(), total(), newList);
    }

    public <R> Pagination<R> map(final Function<T, R> mapper, Comparator<T> comparator) {
        List<R> newList = this.items.stream()
                .sorted(comparator)
                .map(mapper)
                .toList();

        return new Pagination<>(currentPage(), perPage(), total(), newList);
    }
}