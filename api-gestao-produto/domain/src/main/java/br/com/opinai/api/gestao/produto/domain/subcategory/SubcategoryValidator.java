package br.com.opinai.api.gestao.produto.domain.subcategory;

import com.opinai.shared.domain.validation.Error;
import com.opinai.shared.domain.validation.FluentValidator;
import com.opinai.shared.domain.validation.ValidationHandler;
import com.opinai.shared.domain.validation.Validator;

;

public class SubcategoryValidator extends Validator {

    private final Subcategory subcategory;
    private final ValidationHandler handler;

    protected SubcategoryValidator(final Subcategory subcategory, final ValidationHandler handler) {
        super(handler);
        this.subcategory = subcategory;
        this.handler = handler;
    }

    @Override
    public void validate() {
        nameConstraint();
        categoriesConstraint();
        valueObjectConstraints();
    }


    private void nameConstraint() {
        new FluentValidator(this.subcategory.getName(), this.handler)
                .notNullOrEmpty("'name' should not be null or empty")
                .minLength(3, "'name' should be at least 3 characters");
    }

    private void categoriesConstraint() {
        if (this.subcategory.getCategories().isEmpty()) {
            this.handler.append(new Error("'Categories' should not be null or empty"));
        }
    }

    private void valueObjectConstraints() {
        if (this.subcategory.getAggregateId().getNotification().hasError()) {
            this.handler.copy(this.subcategory.getAggregateId().getNotification());
        }
    }
}
