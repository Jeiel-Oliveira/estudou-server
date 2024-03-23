package com.estudou.scheduleservice.advice;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.estudou.scheduleservice.exception.GoalNotFoundException;
import com.estudou.scheduleservice.exception.GoalServiceUnavailableException;
import com.estudou.scheduleservice.exception.ScheduleNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({ScheduleNotFoundException.class, GoalServiceUnavailableException.class, GoalNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleBussinessException2(ResponseStatusException exception) {
        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("status", exception.getStatusCode());
        errorMap.put("message", exception.getReason());
        errorMap.put("code", exception.getStatusCode().value());

        return ResponseEntity
            .status(exception.getStatusCode())
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorMap);
    }
}
