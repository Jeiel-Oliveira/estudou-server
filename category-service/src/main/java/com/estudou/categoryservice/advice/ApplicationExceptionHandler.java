package com.estudou.categoryservice.advice;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.estudou.categoryservice.exception.CategoryNotFoundException;
import com.estudou.categoryservice.exception.ErrorAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, Object> fieldsMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            fieldsMap.put(error.getField(), error.getDefaultMessage());
        });

        ErrorAdvice errorAdvice = new ErrorAdvice(exception.getStatusCode(), "Invalid fields", fieldsMap);
        Map<String, Object> errorParent = errorAdvice.toHashMap();

        return ResponseEntity
            .status(exception.getStatusCode())
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorParent);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBussinessException(CategoryNotFoundException exception) {
        ErrorAdvice errorAdvice = new ErrorAdvice(exception.getStatusCode(), exception.getReason(), null);

        return ResponseEntity
            .status(exception.getStatusCode())
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorAdvice.toHashMap());
    }
}
