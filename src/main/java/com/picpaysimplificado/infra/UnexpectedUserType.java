package com.picpaysimplificado.infra;

public class UnexpectedUserType extends RuntimeException {
    public UnexpectedUserType() {
        super("Tipo de usuário inválido");
    }
}
