package com.brokengate.categoryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CategoryNotFoundException extends ResponseStatusException {
    public CategoryNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "Category not found with the id " + id);
    }
}
