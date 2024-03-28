package com.estudou.scheduleservice.advice;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.estudou.scheduleservice.exception.ErrorAdvice;
import com.estudou.scheduleservice.exception.GoalNotFoundException;
import com.estudou.scheduleservice.exception.GoalServiceUnavailableException;
import com.estudou.scheduleservice.exception.ScheduleNotFoundException;

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

    @ExceptionHandler({ScheduleNotFoundException.class, GoalServiceUnavailableException.class, GoalNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleBussinessException(ResponseStatusException exception) {
        ErrorAdvice errorAdvice = new ErrorAdvice(exception.getStatusCode(), exception.getReason(), null);

        return ResponseEntity
            .status(exception.getStatusCode())
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorAdvice.toHashMap());
    }
}
