package com.picpaysimplificado.infra;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ExceptionDTO> handleInsufficientBalance(InsufficientBalanceException ex) {
        ExceptionDTO dto = new ExceptionDTO(ex.getMessage(), "400");
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(NonAuthorizedUserType.class)
    public ResponseEntity<ExceptionDTO> handleNonAuthorizedUserType(NonAuthorizedUserType ex) {
        ExceptionDTO dto = new ExceptionDTO(ex.getMessage(), "401");
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionDTO> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ExceptionDTO dto = new ExceptionDTO(ex.getMessage(), "400");
        return ResponseEntity.badRequest().body(dto);
    }


}
