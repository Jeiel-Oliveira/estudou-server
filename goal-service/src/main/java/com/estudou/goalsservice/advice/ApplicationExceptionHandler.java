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

import com.estudou.goalsservice.exception.GoalNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseAdvice<Map<String, Object>>> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, Object> fieldsHashMap = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            fieldsHashMap.put(error.getField(), error.getDefaultMessage());
        });

        ResponseAdvice<Map<String, Object>> errorAdvice = new ResponseAdvice<Map<String, Object>>(
            exception.getStatusCode(),
            "Invalid fields",
            fieldsHashMap
        );

        return ResponseEntity
            .status(exception.getStatusCode())
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorAdvice);
    }

    @ExceptionHandler(GoalNotFoundException.class)
    public ResponseEntity<ResponseAdvice<Map<String, Object>>> handleBussinessException (GoalNotFoundException exception) {
        ResponseAdvice<Map<String, Object>> errorAdvice = new ResponseAdvice<Map<String, Object>>(
            exception.getStatusCode(),
            exception.getReason(),
            null
        );

        return ResponseEntity
            .status(exception.getStatusCode())
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorAdvice);
    }
}