package com.opinai.review.infrastructure.converters;


import com.opinai.review.domain.enums.Type;
import com.opinai.shared.domain.utils.EnumUtils;

import javax.persistence.AttributeConverter;

public class TypeConverter implements AttributeConverter<Type, String> {
    @Override
    public String convertToDatabaseColumn(final Type status) {
        if (status == null) return null;
        return status.getValue();
    }

    @Override
    public Type convertToEntityAttribute(String data) {
        if (data == null) return null;
        return EnumUtils.of(Type.values(), data); //Type.of(data).orElse(null);
    }
}
