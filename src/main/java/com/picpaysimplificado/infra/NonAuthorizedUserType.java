package com.picpaysimplificado.infra;

public class NonAuthorizedUserType extends RuntimeException {
    public NonAuthorizedUserType() {
        super ("Usuário do tipo Lojista não está autorizado a realizar transação");
    }
}
