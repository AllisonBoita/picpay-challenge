package com.picpaysimplificado.infra;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Saldo insuficiente para realizar transação");
    }
}
