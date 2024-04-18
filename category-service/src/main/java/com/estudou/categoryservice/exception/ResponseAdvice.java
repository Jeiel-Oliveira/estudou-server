package com.estudou.categoryservice.exception;

import org.springframework.http.HttpStatusCode;

import brave.internal.Nullable;

/**
 * Generic class representing a response advice. This class encapsulates
 * information about an HTTP response, including status, message, code, and
 * optional data.
 *
 * @param <T> The type of data included in the response.
 */
public class ResponseAdvice<T> {

  private HttpStatusCode status;

  private String message;

  private int code;

  @Nullable
  private T data;

  /**
   * Constructs a new ResponseAdvice object with the specified status, message,
   * and data.
   *
   * @param status  The HTTP status code of the response.
   * @param message The message associated with the response.
   * @param data    The optional data included in the response.
   */
  public ResponseAdvice(HttpStatusCode status, String message, @Nullable T data) {
    this.status = status;
    this.code = status.value();
    this.message = message;
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public HttpStatusCode getStatus() {
    return status;
  }

  public int getCode() {
    return code;
  }

  public T getData() {
    return data;
  }
}