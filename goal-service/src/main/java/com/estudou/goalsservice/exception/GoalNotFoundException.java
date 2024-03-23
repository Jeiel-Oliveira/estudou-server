package com.estudou.goalsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GoalNotFoundException extends ResponseStatusException {
    public GoalNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "Goal not found with the id " + id);
    }
}