package com.picpaysimplificado.infra;

import org.springframework.http.HttpStatus;

public class ForbiddenUserTypeException extends RuntimeException {
    public ForbiddenUserTypeException() {
        super("Usuário do tipo Lojista não está autorizado a realizar transação");
    }
}