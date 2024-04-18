package com.estudou.scheduleservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception indicating that a schedule with the specified ID was not found.
 * This exception is thrown when attempting to retrieve a schedule that does not
 * exist.
 */
public class ScheduleNotFoundException extends ResponseStatusException {
  public ScheduleNotFoundException(String id) {
    super(HttpStatus.NOT_FOUND, "Schedule not found with the id " + id);
  }
}
