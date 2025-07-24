package br.com.opinai.api.review.infrastructure.converters;


import br.com.opinai.api.review.domain.enums.Status;
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
