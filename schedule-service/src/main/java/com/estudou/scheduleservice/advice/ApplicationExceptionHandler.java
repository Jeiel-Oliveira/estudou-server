package com.estudou.scheduleservice.advice;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.estudou.scheduleservice.exception.GoalNotFoundException;
import com.estudou.scheduleservice.exception.GoalServiceUnavailableException;
import com.estudou.scheduleservice.exception.ScheduleNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(GoalNotFoundException.class)
    public Map<String, Object> handleBussinessException(GoalNotFoundException exception) {
        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("status", exception.getStatusCode());
        errorMap.put("message", exception.getReason());

        return errorMap;
    }

    // TODO - DUPLICATED handleBussinessException
    @ExceptionHandler(ScheduleNotFoundException.class)
    public Map<String, Object> handleBussinessException2(ScheduleNotFoundException exception) {
        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("status", exception.getStatusCode());
        errorMap.put("message", exception.getReason());

        return errorMap;
    }

    // TODO - DUPLICATED handleBussinessException
    @ExceptionHandler(GoalServiceUnavailableException.class)
    public Map<String, Object> handleBussinessException3(GoalServiceUnavailableException exception) {
        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("status", exception.getStatusCode());
        errorMap.put("message", exception.getReason());

        return errorMap;
    }
}
