package br.com.opinai.api.conta.domain.exceptions;


import com.opinai.shared.domain.exceptions.BadRequestException;
import com.opinai.shared.domain.validation.Error;

import java.util.List;

public class UserNotFoundException extends BadRequestException {

    public UserNotFoundException() {
        super(List.of(new Error("Usuário não cadastradso.")));
    }
}
