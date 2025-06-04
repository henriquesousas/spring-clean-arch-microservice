package com.jobee.admin.service.infrastructure.review;

import com.jobee.admin.service.domain.review.enums.Type;

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
        return Type.of(data).orElse(null);
    }
}
