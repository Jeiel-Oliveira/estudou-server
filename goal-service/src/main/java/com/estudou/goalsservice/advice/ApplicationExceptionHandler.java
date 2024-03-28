package com.estudou.goalsservice.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.estudou.goalsservice.exception.ErrorAdvice;
import com.estudou.goalsservice.exception.GoalNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, Object> fieldsHashMap = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            fieldsHashMap.put(error.getField(), error.getDefaultMessage());
        });

        ErrorAdvice errorAdvice = new ErrorAdvice(exception.getStatusCode(), "Invalid fields", fieldsHashMap);
        Map<String, Object> errorParent = errorAdvice.toHashMap();

        return ResponseEntity
            .status(exception.getStatusCode())
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorParent);
    }

    @ExceptionHandler(GoalNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBussinessException (GoalNotFoundException exception) {
        ErrorAdvice errorAdvice = new ErrorAdvice(exception.getStatusCode(), exception.getReason(), null);
        Map<String, Object> errorMap = errorAdvice.toHashMap();

        return ResponseEntity
            .status(exception.getStatusCode())
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorMap);
    }
}