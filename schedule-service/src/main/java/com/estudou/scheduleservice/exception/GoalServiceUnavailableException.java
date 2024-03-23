package com.estudou.scheduleservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GoalServiceUnavailableException extends ResponseStatusException {
    public GoalServiceUnavailableException (String reason) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, reason);
    }
}
