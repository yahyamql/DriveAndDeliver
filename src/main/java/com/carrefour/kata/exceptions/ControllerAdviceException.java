package com.carrefour.kata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdviceException {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(Exception ex) {
        return new ResponseEntity<>(
                ex.getMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        List<String> details = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            details.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return new ResponseEntity<>(details.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<String> handleGeneralException() {
        return new ResponseEntity<>("Une erreur interne est survenue !", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
