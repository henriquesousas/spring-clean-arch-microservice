package com.jobee.admin.service.domain.category;

import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.validation.ValidationHandler;
import com.jobee.admin.service.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private Category category;

    protected CategoryValidator(final Category category, final ValidationHandler handler) {
        super(handler);
        this.category = category;

    }

    @Override
    public void validate() {
        if (this.category.getName() == null || this.category.getName().isEmpty()) {
            this.validationHandler().append(new Error("'name' should not be null or empty"));
            return;
        }

        if (this.category.getName().trim().length() < 3 || this.category.getName().trim().length() > 255) {
            this.validationHandler().append(new Error(("'name' must be between 3 and 255 characters")));
        }
    }
}
