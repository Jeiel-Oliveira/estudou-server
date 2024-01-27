package com.example.missionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MissionNotFoundException extends ResponseStatusException {
    public MissionNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "Mission not found with the id " + id);
    }
}
