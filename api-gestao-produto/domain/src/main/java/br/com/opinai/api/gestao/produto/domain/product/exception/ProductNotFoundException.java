package br.com.opinai.api.gestao.produto.domain.product.exception;

import com.opinai.shared.domain.exceptions.NotFoundException;
import com.opinai.shared.domain.validation.Error;

import java.util.List;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException() {
        super("NotFound", List.of(new Error("Produto não encontrado.")));
    }
}
