package com.picpaysimplificado.infra;

import com.picpaysimplificado.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDTO> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        String mensagem = "Erro de integridade nos dados";

        // Aqui você pode inspecionar a mensagem original do erro
        String erroOriginal = exception.getMostSpecificCause().getMessage();

        if (erroOriginal.contains("users.document") || erroOriginal.contains("users.email")) {
            mensagem = "Usuário já cadastrado com este documento ou email";
        } else if (erroOriginal.contains("Usuário do tipo Lojista")) {
            mensagem = "Usuário logista não pode realizar transações";
        }

        ExceptionDTO exceptionDTO = new ExceptionDTO(mensagem, "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> threatGeneralException(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ExceptionDTO> handleInsufficientBalance(InsufficientBalanceException ex) {
        return ResponseEntity
                .status(400)
                .body(new ExceptionDTO(ex.getMessage(), "400"));
    }
}
