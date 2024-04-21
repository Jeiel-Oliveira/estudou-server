package com.estudou.scheduleservice.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.estudou.scheduleservice.exception.GenericResponseException;
import com.estudou.scheduleservice.exception.GoalNotFoundException;
import com.estudou.scheduleservice.exception.GoalServiceUnavailableException;
import com.estudou.scheduleservice.exception.ScheduleNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Global exception handler for the application. This class provides centralized
 * exception handling for specific exceptions thrown within the application.
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {

  /**
   * Handles MethodArgumentNotValidException thrown when method arguments fail
   * validation.
   *
   * @param exception The MethodArgumentNotValidException instance.
   * @return A ResponseEntity containing error details for invalid method
   *         arguments.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseAdvice<Map<String, Object>>> handleInvalidArgument(
      MethodArgumentNotValidException exception) {
    Map<String, Object> fieldsMap = new HashMap<>();
    exception.getBindingResult().getFieldErrors().forEach(error -> {
      fieldsMap.put(error.getField(), error.getDefaultMessage());
    });

    ResponseAdvice<Map<String, Object>> errorAdvice = new ResponseAdvice<Map<String, Object>>(
        exception.getStatusCode(), "Invalid fields", fieldsMap);

    return ResponseEntity.status(exception.getStatusCode()).contentType(MediaType.APPLICATION_JSON)
        .body(errorAdvice);
  }

  /**
   * Handles multiple exceptions related to business logic.
   *
   * @param exception The ResponseStatusException instance.
   * @return A ResponseEntity containing error details for business-related
   *         exceptions.
   */
  @ExceptionHandler({ ScheduleNotFoundException.class, GoalServiceUnavailableException.class,
      GoalNotFoundException.class, GenericResponseException.class })
  public ResponseEntity<ResponseAdvice<Map<String, Object>>> handleBussinessException(
      ResponseStatusException exception) {
    ResponseAdvice<Map<String, Object>> errorAdvice = new ResponseAdvice<Map<String, Object>>(
        exception.getStatusCode(), exception.getReason(), null);

    return ResponseEntity.status(exception.getStatusCode()).contentType(MediaType.APPLICATION_JSON)
        .body(errorAdvice);
  }

  @ExceptionHandler(JsonProcessingException.class)
  public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException ex) {
    return new ResponseEntity<>("Error processing JSON", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
