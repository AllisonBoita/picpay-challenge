package com.picpaysimplificado.infra;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ExceptionDTO> handleInsufficientBalance(InsufficientBalanceException ex) {
        ExceptionDTO dto = new ExceptionDTO(ex.getMessage(), 422);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(dto);
    }

    @ExceptionHandler(ForbiddenUserTypeException.class)
    public ResponseEntity<ExceptionDTO> handleNonAuthorizedUserType(ForbiddenUserTypeException ex) {
        ExceptionDTO dto = new ExceptionDTO(ex.getMessage(), 403);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dto);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionDTO> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ExceptionDTO dto = new ExceptionDTO(ex.getMessage(), 409);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
    }

    @ExceptionHandler(UnexpectedUserType.class)
    public ResponseEntity<ExceptionDTO> handleUnexpectedUserTypeException(UnexpectedUserType ex){
        ExceptionDTO dto = new ExceptionDTO(ex.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDTO> handleJsonParseError(HttpMessageNotReadableException ex) {
        Throwable rootCause = ex.getCause();

        if (rootCause instanceof InvalidFormatException invalidFormatException &&
                invalidFormatException.getTargetType().isEnum()) {

            String fieldName = invalidFormatException.getPath().get(0).getFieldName();
            Class<?> enumType = invalidFormatException.getTargetType();
            String valoresPermitidos = Arrays.stream(enumType.getEnumConstants())
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));

            String msg = String.format(
                    "Valor inválido para o campo '%s'. Valores permitidos: %s",
                    fieldName, valoresPermitidos
            );

            return ResponseEntity
                    .badRequest()
                    .body(new ExceptionDTO(msg, 400));
        }

        return ResponseEntity
                .badRequest()
                .body(new ExceptionDTO("Erro na leitura do corpo da requisição", 400));
    }


}
