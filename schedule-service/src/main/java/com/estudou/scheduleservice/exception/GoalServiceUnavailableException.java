package com.estudou.scheduleservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception indicating that the goal service is unavailable. This exception is
 * thrown when there is an issue with the goal service that prevents it from
 * fulfilling a request.
 */
public class GoalServiceUnavailableException extends ResponseStatusException {
  public GoalServiceUnavailableException(String reason) {
    super(HttpStatus.UNPROCESSABLE_ENTITY, reason);
  }
}
