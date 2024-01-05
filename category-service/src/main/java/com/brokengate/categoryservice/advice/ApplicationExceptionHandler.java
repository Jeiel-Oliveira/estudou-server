package com.brokengate.categoryservice.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.brokengate.categoryservice.exception.CategoryNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public Map<String, Object> handleBussinessException(CategoryNotFoundException exception) {
        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("status", exception.getStatusCode());
        errorMap.put("message", exception.getReason());

        return errorMap;
    }

}
