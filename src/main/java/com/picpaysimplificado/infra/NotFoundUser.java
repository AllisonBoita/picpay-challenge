package com.picpaysimplificado.infra;

public class NotFoundUser extends RuntimeException {
    public NotFoundUser(){
        super("Usuário não encontrado");
    }
}
