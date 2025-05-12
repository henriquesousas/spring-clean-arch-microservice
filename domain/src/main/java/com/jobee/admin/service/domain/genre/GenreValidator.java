package com.jobee.admin.service.domain.genre;

import com.jobee.admin.service.domain.shared.validation.Error;
import com.jobee.admin.service.domain.shared.validation.ValidationHandler;
import com.jobee.admin.service.domain.shared.validation.Validator;

public class GenreValidator extends Validator {

    private final Genre genre;
    private final ValidationHandler handler;

    protected GenreValidator(final Genre genre, final ValidationHandler handler) {
        super(handler);
        this.genre = genre;
        this.handler = handler;
    }

    @Override
    public void validate() {
        nameConstraint();
        descriptionConstraint();
        categoriesConstraint();
        valueObjectConstraints();
    }

    private void descriptionConstraint() {
        if (this.genre.getDescription() == null || this.genre.getDescription().isBlank()) {
            this.handler.append(new Error("'description' should not be null or empty"));
        }

        if (this.genre.getDescription().length() < 3) {
            this.handler.append(new Error("'description' should be at least 3 characters"));
        }
    }

    private void nameConstraint() {
        if (this.genre.getName() == null || this.genre.getName().isBlank()) {
            this.handler.append(new Error("'name' should not be null or empty"));
        }

        if (this.genre.getName().length() < 3) {
            this.handler.append(new Error("'name' should be at least 3 characters"));
        }
    }

    private void categoriesConstraint() {
        if (this.genre.getCategories().isEmpty()) {
            this.handler.append(new Error("'Categories' should not be null or empty"));
        }
    }

    private void valueObjectConstraints() {
        if (this.genre.getAggregateId().getNotification().hasError()) {
            this.handler.copy(this.genre.getAggregateId().getNotification());
        }
    }
}
