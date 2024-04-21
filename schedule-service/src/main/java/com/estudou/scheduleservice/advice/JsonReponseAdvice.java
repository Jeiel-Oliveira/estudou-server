package com.estudou.scheduleservice.advice;

import brave.internal.Nullable;

/**
 * A generic class for creating JSON responses containing status, message, code,
 * and optional data.
 *
 * @param <T> The type of data to be included in the response.
 */
public class JsonReponseAdvice<T> {

  private String status;

  private String message;

  private String code;

  @Nullable
  private T data;

  /**
   * Constructs a JsonResponseAdvice object with the given status, message, and
   * data.
   *
   * @param status  The status of the response.
   * @param message A message describing the response.
   * @param data    The data to be included in the response (can be null).
   */
  public JsonReponseAdvice(String status, String message, @Nullable T data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public JsonReponseAdvice() {
    super();
  }

  public String getMessage() {
    return message;
  }

  public String getStatus() {
    return status;
  }

  public String getCode() {
    return code;
  }

  public T getData() {
    return data;
  }
}
