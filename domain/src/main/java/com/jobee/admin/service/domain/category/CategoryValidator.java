package com.jobee.admin.service.domain.category;

import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.validation.ValidationHandler;
import com.jobee.admin.service.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category category, final ValidationHandler handler) {
        super(handler);
        this.category = category;

    }

    @Override
    public void validate() {
        nameConstraint();
        descriptionConstraint();
        valueObjectConstraint();
    }

    private void valueObjectConstraint() {

        if (this.category.getId().getNotification().hasError()) {
            this.validationHandler().copy(this.category.getId().getNotification());
        }

    }

    private void nameConstraint() {

        if (this.category
                .getName() == null || this.category.getName().isEmpty()) {
            this.validationHandler().append(new Error("'name' should not be null or empty"));
        }

        if (this.category.getName().trim().length() < 3 || this.category.getName().trim().length() > 255) {
            this.validationHandler().append(new Error(("'name' must be between 3 and 255 characters")));
        }
    }

    private void descriptionConstraint() {

        if (this.category.getDescription() == null || this.category.getDescription().isEmpty()) {
            this.validationHandler().append(new Error("'description' should not be null or empty"));
        }
    }
}
