package com.brokengate.categoryservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brokengate.categoryservice.dto.CategoryRequest;
import com.brokengate.categoryservice.model.Category;
import com.brokengate.categoryservice.service.CategoryService;

import java.util.List;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.create(categoryRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> findAll() {
        List<Category> categories = categoryService.findAll();
        return categories;
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public Category findById(@PathVariable(value="categoryId") String categoryId) {
        return categoryService.findById(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable(value="categoryId") String categoryId) {
        categoryService.delete(categoryId);
        return String.format("Category %s successfully deleted", categoryId);
    }
}
