package com.estudou.scheduleservice.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception indicating a generic request mapping. This
 * exception is thrown when attempting to call another microservice.
 */
public class GenericResponseException extends ResponseStatusException {
  public GenericResponseException(HttpStatusCode status, String reason) {
    super(status, reason);
  }
}
