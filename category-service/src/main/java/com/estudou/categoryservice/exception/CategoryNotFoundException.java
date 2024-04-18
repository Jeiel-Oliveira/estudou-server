package com.estudou.categoryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception class representing a category not found error.
 * This exception is thrown when attempting to retrieve a category that does not exist.
 */
public class CategoryNotFoundException extends ResponseStatusException {
  public CategoryNotFoundException(String id) {
    super(HttpStatus.NOT_FOUND, "Category not found with the id " + id);
  }
}
