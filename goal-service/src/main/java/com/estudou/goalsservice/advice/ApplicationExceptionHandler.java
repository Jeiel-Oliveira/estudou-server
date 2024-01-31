package com.estudou.goalsservice.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.estudou.goalsservice.exception.GoalNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, Object> errorParent = new HashMap<>();
        errorParent.put("status", exception.getStatusCode());

        Map<String, Object> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        errorParent.put("data", errorMap);
        errorParent.put("message", "Invalid fields");

        return errorParent;
    }

    @ExceptionHandler(GoalNotFoundException.class)
    public Map<String, Object> handleBussinessException (GoalNotFoundException exception) {
        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("status", exception.getStatusCode());
        errorMap.put("message", exception.getReason());

        return errorMap;
    }

}