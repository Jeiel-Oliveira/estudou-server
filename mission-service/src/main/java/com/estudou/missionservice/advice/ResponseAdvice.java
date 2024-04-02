package com.estudou.missionservice.advice;

import org.springframework.http.HttpStatusCode;
import brave.internal.Nullable;

public class ResponseAdvice<T> {

    private HttpStatusCode status;

    private String message;

    private int code;

    @Nullable
	private T data;

    public ResponseAdvice (HttpStatusCode status, String message, @Nullable T data) {
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