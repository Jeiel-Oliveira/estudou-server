package com.estudou.categoryservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estudou.categoryservice.dto.CategoryRequest;
import com.estudou.categoryservice.exception.ResponseAdvice;
import com.estudou.categoryservice.model.Category;
import com.estudou.categoryservice.service.CategoryService;
import jakarta.validation.Valid;

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
    public ResponseAdvice<Category> create(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.create(categoryRequest);

        ResponseAdvice<Category> responseAdvice = new ResponseAdvice<Category>(
            HttpStatus.CREATED,
            "Category created successfully.",
            category
        );

        return  responseAdvice;
    }

    @PatchMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseAdvice<Category> update(@PathVariable(value="categoryId") String categoryId, @Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.update(categoryId, categoryRequest);

        ResponseAdvice<Category> responseAdvice = new ResponseAdvice<Category>(
            HttpStatus.OK,
            "Category updated successfully.",
            category
        );

        return responseAdvice;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseAdvice<List<Category>> findAll() {
        List<Category> categories = categoryService.findAll();

        ResponseAdvice<List<Category>> responseAdvice = new ResponseAdvice<List<Category>>(
            HttpStatus.OK,
            "Categories found successfully.",
            categories
        );

        return responseAdvice;
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseAdvice<Category> findById(@PathVariable(value="categoryId") String categoryId) {
        Category category = categoryService.findById(categoryId);

        ResponseAdvice<Category> responseAdvice = new ResponseAdvice<Category>(
            HttpStatus.OK,
            "Category found successfully",
            category
        );

        return responseAdvice;
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseAdvice<String> delete(@PathVariable(value="categoryId") String categoryId) {
        categoryService.delete(categoryId);

        ResponseAdvice<String> responseAdvice = new ResponseAdvice<String>(
            HttpStatus.OK,
            String.format("Category %s successfully deleted", categoryId),
            null
        );

        return responseAdvice;
    }
}
