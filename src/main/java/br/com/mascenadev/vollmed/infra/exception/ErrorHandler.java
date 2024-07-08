package br.com.mascenadev.vollmed.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandler404() {

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandler400(MethodArgumentNotValidException e) {

        var errors = e.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream()
                .map(DataErrorValidation::new).toList());
    }

    private record DataErrorValidation(
            String field,
            String message) {

        public DataErrorValidation(FieldError field) {
            this(field.getField(), field.getDefaultMessage());
        }
    }
}