package com.picpaysimplificado.infra;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Usuário já cadastrado");
    }
}
