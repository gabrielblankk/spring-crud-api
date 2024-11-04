package com.blankk.springapi.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFound() {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", "Produto não encontrado");

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequest(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
