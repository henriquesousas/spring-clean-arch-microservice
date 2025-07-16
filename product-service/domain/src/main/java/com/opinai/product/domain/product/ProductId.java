package com.opinai.product.domain.product;

import com.opinai.shared.domain.Identifier;
import com.opinai.shared.domain.utils.IdUtils;

public class ProductId extends Identifier {
    public ProductId(String value) {
        super(value);
    }

    public static ProductId unique() {
        return new ProductId(IdUtils.uuid());
    }

    public static ProductId from(String value) {
        return new ProductId(value);
    }
}
