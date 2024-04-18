package com.estudou.missionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception class representing a mission not found error. This exception is
 * thrown when attempting to retrieve a mission that does not exist.
 */
public class MissionNotFoundException extends ResponseStatusException {
  public MissionNotFoundException(String id) {
    super(HttpStatus.NOT_FOUND, "Mission not found with the id " + id);
  }
}
