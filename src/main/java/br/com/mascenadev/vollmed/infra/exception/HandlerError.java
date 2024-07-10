package br.com.mascenadev.vollmed.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerError {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity HandlerError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity HandleError400(MethodArgumentNotValidException ex) {

        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorValidation::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity HandleError400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity HandlerErrorBadCredentials() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity HandlerErrorAuthentication() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Falha na autenticação");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity HandleAccessDeniedError() {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Acesso negado");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity HandlerError500(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro: " + ex.getLocalizedMessage());
    }

    private record DataErrorValidation(
            String field,
            String message) {

        public DataErrorValidation(FieldError field) {
            this(field.getField(), field.getDefaultMessage());
        }
    }
}