package com.estudou.goalsservice.exception;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import brave.internal.Nullable;

public class ErrorAdvice {

    private HttpStatusCode status;

    private String message;

    @Nullable
	private Map<String, Object> data;

    public ErrorAdvice (HttpStatusCode status, String message, @Nullable Map<String, Object> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public Map<String, Object> toHashMap () {
        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("code", this.status.value());
        errorMap.put("status", this.status);
        errorMap.put("message", this.message);
        if (this.data != null) errorMap.put("data", this.data);

        return errorMap;
    }
}