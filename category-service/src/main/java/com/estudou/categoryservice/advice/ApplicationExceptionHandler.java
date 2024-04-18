package com.estudou.categoryservice.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.estudou.categoryservice.exception.CategoryNotFoundException;
import com.estudou.categoryservice.exception.ResponseAdvice;

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
   * Handles CategoryNotFoundException thrown when a category is not found.
   *
   * @param exception The CategoryNotFoundException instance.
   * @return A ResponseEntity containing error details for category not found.
   */
  @ExceptionHandler(CategoryNotFoundException.class)
  public ResponseEntity<ResponseAdvice<Map<String, Object>>> handleBussinessException(
      CategoryNotFoundException exception) {
    ResponseAdvice<Map<String, Object>> errorAdvice = new ResponseAdvice<Map<String, Object>>(
        exception.getStatusCode(), exception.getReason(), null);

    return ResponseEntity.status(exception.getStatusCode()).contentType(MediaType.APPLICATION_JSON)
        .body(errorAdvice);
  }
}
