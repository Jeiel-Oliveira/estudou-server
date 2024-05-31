package com.estudou.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * This exception is used to indicate that a user with a specified ID does not
 * exist in the system. It extends {@link ResponseStatusException} and sets the
 * HTTP status to {@code NOT_FOUND}.
 *
 * @see ResponseStatusException
 */
public class UserNotFoundException extends ResponseStatusException {
  public UserNotFoundException(String id) {
    super(HttpStatus.NOT_FOUND, "User not found with the id " + id);
  }
}
