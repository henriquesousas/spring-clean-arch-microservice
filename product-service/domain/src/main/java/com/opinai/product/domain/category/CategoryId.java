package com.opinai.product.domain.category;

import com.opinai.shared.domain.Identifier;
import com.opinai.shared.domain.utils.IdUtils;

import java.util.List;
import java.util.UUID;

public class CategoryId extends Identifier {

    private CategoryId(String value) {
        super(value);
    }

    public static CategoryId unique() {
        return CategoryId.from(IdUtils.uuid());
    }

    public static CategoryId from(final String id) {
        return new CategoryId(id);
    }

    public static CategoryId from(final UUID uuid) {
        return new CategoryId(uuid.toString().toLowerCase());
    }

    public static List<CategoryId> from(final List<String> categories) {
        return categories.stream()
                .map(CategoryId::from)
                .filter(c -> !c.getNotification().hasError())
                .distinct()
                .toList();
    }
}
