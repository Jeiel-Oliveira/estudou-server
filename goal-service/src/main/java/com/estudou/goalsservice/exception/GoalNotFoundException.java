package com.estudou.goalsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception class representing a goal not found error. This exception is thrown
 * when attempting to retrieve a goal that does not exist.
 */
public class GoalNotFoundException extends ResponseStatusException {
  public GoalNotFoundException(String id) {
    super(HttpStatus.NOT_FOUND, "Goal not found with the id " + id);
  }
}