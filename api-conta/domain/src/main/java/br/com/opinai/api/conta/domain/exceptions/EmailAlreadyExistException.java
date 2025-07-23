package br.com.opinai.api.conta.domain.exceptions;


import com.opinai.shared.domain.exceptions.BadRequestException;
import com.opinai.shared.domain.validation.Error;

import java.util.List;

public class EmailAlreadyExistException extends BadRequestException {

    public EmailAlreadyExistException() {
        super(List.of(new Error("Usuário já cadastrado.")));
    }
}
