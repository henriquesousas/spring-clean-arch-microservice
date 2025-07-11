package com.opinai.review.infrastructure.converters;


import com.opinai.review.domain.enums.Status;
import com.opinai.shared.domain.utils.EnumUtils;

import javax.persistence.AttributeConverter;


public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(final Status status) {

        if (status == null) return null;
        return  status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String data) {
        if (data == null) return null;
        return EnumUtils.of(Status.values(), data);
    }
}
