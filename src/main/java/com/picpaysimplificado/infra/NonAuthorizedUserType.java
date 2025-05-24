package com.picpaysimplificado.infra;

import org.springframework.http.HttpStatus;

public class NonAuthorizedUserType extends RuntimeException {
    public NonAuthorizedUserType() {
        super ("Usuário do tipo Lojista não está autorizado a realizar transação");
    }
}
