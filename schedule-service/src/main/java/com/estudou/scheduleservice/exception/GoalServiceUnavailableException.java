package com.estudou.scheduleservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GoalServiceUnavailableException extends ResponseStatusException {
    public GoalServiceUnavailableException () {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Something went wrong, please try again after some time.");
    }
}
