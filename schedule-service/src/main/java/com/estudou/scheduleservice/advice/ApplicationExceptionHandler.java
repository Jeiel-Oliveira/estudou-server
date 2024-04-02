package com.estudou.scheduleservice.advice;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.estudou.scheduleservice.exception.GoalNotFoundException;
import com.estudou.scheduleservice.exception.GoalServiceUnavailableException;
import com.estudou.scheduleservice.exception.ScheduleNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseAdvice<Map<String, Object>>> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, Object> fieldsMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            fieldsMap.put(error.getField(), error.getDefaultMessage());
        });

        ResponseAdvice<Map<String, Object>> errorAdvice = new ResponseAdvice<Map<String, Object>>(
            exception.getStatusCode(),
            "Invalid fields",
            fieldsMap
        );

        return ResponseEntity
            .status(exception.getStatusCode())
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorAdvice);
    }

    @ExceptionHandler({ScheduleNotFoundException.class, GoalServiceUnavailableException.class, GoalNotFoundException.class})
    public ResponseEntity<ResponseAdvice<Map<String, Object>>> handleBussinessException(ResponseStatusException exception) {
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
