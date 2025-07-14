package com.opinai.product.domain.genre;

import com.opinai.shared.domain.validation.Error;
import com.opinai.shared.domain.validation.FluentValidator;
import com.opinai.shared.domain.validation.ValidationHandler;
import com.opinai.shared.domain.validation.Validator;

;

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
        new FluentValidator(this.genre.getDescription(), this.handler)
                .minLength(3, "'description' should be at least 3 characters")
                .notNullOrEmpty("'description' should not be null or empty");
    }

    private void nameConstraint() {
        new FluentValidator(this.genre.getName(), this.handler)
                .notNullOrEmpty("'name' should not be null or empty")
                .minLength(3, "'name' should be at least 3 characters");
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
