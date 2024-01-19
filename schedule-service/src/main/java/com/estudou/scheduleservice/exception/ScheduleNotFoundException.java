package com.estudou.scheduleservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ScheduleNotFoundException extends ResponseStatusException {
    public ScheduleNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "Schedule not found with the id " + id);
    }
}
