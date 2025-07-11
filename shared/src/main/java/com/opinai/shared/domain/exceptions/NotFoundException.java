package com.opinai.shared.domain.exceptions;


import com.opinai.shared.domain.AggregateRoot;
import com.opinai.shared.domain.Identifier;
import com.opinai.shared.domain.validation.Error;

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
        return new NotFoundException("NotFound", List.of(new Error(anError)));
    }

    public static NotFoundException with(Error error) {
        return new NotFoundException(error.message(), List.of(error));
    }

    @Override
    public int getStatus() {
        return 404;
    }
}
