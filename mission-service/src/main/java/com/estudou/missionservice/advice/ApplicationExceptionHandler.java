package com.estudou.missionservice.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.estudou.missionservice.exception.MissionNotFoundException;

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
   * Handles MissionNotFoundException thrown when a mission is not found.
   *
   * @param exception The MissionNotFoundException instance.
   * @return A ResponseEntity containing error details for mission not found.
   */
  @ExceptionHandler(MissionNotFoundException.class)
  public ResponseEntity<ResponseAdvice<Map<String, Object>>> handleBussinessException(
      MissionNotFoundException exception) {
    ResponseAdvice<Map<String, Object>> errorAdvice = new ResponseAdvice<Map<String, Object>>(
        exception.getStatusCode(), exception.getReason(), null);

    return ResponseEntity.status(exception.getStatusCode()).contentType(MediaType.APPLICATION_JSON)
        .body(errorAdvice);
  }
}