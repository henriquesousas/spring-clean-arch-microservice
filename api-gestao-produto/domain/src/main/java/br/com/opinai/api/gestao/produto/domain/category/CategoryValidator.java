<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/CategoryValidator.java
package com.opinai.product.domain.category;
========
package br.com.opinai.api.gestao.produto.domain.category;
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/CategoryValidator.java


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
<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/CategoryValidator.java
        descriptionConstraint();
========
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/CategoryValidator.java
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
<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/CategoryValidator.java

    private void descriptionConstraint() {
        if (this.category.getDescription() == null || this.category.getDescription().isEmpty()) {
            this.validationHandler().append(new Error("'description' should not be null or empty"));
        }
    }
========
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/CategoryValidator.java
}
