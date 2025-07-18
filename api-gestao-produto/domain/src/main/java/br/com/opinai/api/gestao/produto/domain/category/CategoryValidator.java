package br.com.opinai.api.gestao.produto.domain.category;


import com.opinai.shared.domain.validation.Error;
import com.opinai.shared.domain.validation.ValidationHandler;
import com.opinai.shared.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category category, final ValidationHandler handler) {
        super(handler);
        this.category = category;

    }

    @Override
    public void validate() {
        nameConstraint();
        valueObjectConstraint();
    }

    private void valueObjectConstraint() {
        if (this.category.getId().getNotification().hasError()) {
            this.validationHandler().copy(this.category.getId().getNotification());
        }
    }

    private void nameConstraint() {
        if (this.category.getName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (this.category.getName().isEmpty()) {
            this.validationHandler().append(new Error("'name' should not be null or empty"));
        }

        if (this.category.getName().trim().length() < 3 || this.category.getName().trim().length() > 255) {
            this.validationHandler().append(new Error(("'name' must be between 3 and 255 characters")));
        }
    }
}
