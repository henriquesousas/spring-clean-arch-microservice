package com.jobee.admin.service.application;

import com.jobee.admin.service.domain.category.Category;

import java.lang.reflect.Field;
import java.util.Comparator;

public class Sort {

    private Comparator comparator;

    public Sort(Comparator comparator) {
        this.comparator = comparator;
    }

    //TODO: Refactor
    public static Sort with(String fieldName, boolean descending) {
        try {
            Field field = Category.class.getDeclaredField(fieldName);
            field.setAccessible(true);

            var comparator = Comparator.comparing(category -> {
                try {
                    return (Comparable) field.get(category);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });

            return descending
                    ? new Sort(comparator.reversed())
                    : new Sort(comparator);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Campo não encontrado: " + fieldName, e);
        }
    }

    public Comparator get() {
        return comparator;
    }
}
