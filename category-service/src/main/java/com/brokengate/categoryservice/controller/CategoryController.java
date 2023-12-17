package com.brokengate.categoryservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brokengate.categoryservice.dto.CategoryRequest;
import com.brokengate.categoryservice.model.Category;
import com.brokengate.categoryservice.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
	private ObjectMapper objectMapper;

    private final CategoryService categoryService;    

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.create(categoryRequest);
        return "Category successfully created";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getAllCategory() {
        try {
            List<Category> categories = categoryService.findAll();
            String categoriesAsString = objectMapper.writeValueAsString(categories);

            return categoriesAsString;
        } catch (JsonProcessingException error) {
            return "Error";
        }
    }
}
