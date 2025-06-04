package com.jobee.admin.service.infrastructure.review;

import com.jobee.admin.service.domain.review.enums.Status;

import javax.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(final Status status) {
        if (status == null) return null;
        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String data) {
        if (data == null) return null;
        return Status.of(data).orElse(null);
    }
}
