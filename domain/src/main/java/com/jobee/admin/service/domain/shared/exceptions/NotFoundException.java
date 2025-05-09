package com.jobee.admin.service.domain.shared.exceptions;

import com.jobee.admin.service.domain.shared.AggregateRoot;
import com.jobee.admin.service.domain.shared.Identifier;
import com.jobee.admin.service.domain.shared.validation.Error;

import java.util.List;

public class NotFoundException extends DomainException {

    protected NotFoundException(final String message, final List<Error> errors) {
        super(message, errors);
    }

    public static NotFoundException with(
            final Class<? extends AggregateRoot<?>> aggregateRoot,
            final Identifier identifier
    ) {
        final var anError = "%s with ID %s was not found".formatted(
                aggregateRoot.getSimpleName(),
                identifier.getValue()
        );
        return new NotFoundException("NotFound",List.of(new Error(anError)));
    }

    public static NotFoundException with(Error error) {
        return  new NotFoundException(error.message(), List.of(error));
    }

    @Override
    public int getStatus() {
        return 404;
    }
}
