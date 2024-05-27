package com.estudou.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {
  public UserNotFoundException(String id) {
    super(HttpStatus.NOT_FOUND, "User not found with the id " + id);
  }
}
