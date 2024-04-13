package com.estudou.categoryservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import com.estudou.categoryservice.dto.CategoryRequest;
import com.estudou.categoryservice.model.Category;
import com.estudou.categoryservice.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CategoryController.class)
class CategoryControllerTests {

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreate() throws Exception {
        CategoryRequest categoryRequest = generateCategoryRequest();
        String categoryRequestToString = objectMapper.writeValueAsString(categoryRequest);

        when(categoryService.create(any())).thenReturn(generateCategory());

        mockMvc.perform(
            post("/api/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryRequestToString)
        ).andExpect(status().isCreated());
    }

    @Test
    void shouldFailCreationWithoutName() throws Exception {
        mockMvc.perform(
            post("/api/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdate() throws Exception {
        CategoryRequest categoryRequest = generateCategoryRequest();
        String categoryRequestString = objectMapper.writeValueAsString(categoryRequest);

        mockMvc.perform(
            patch("/api/category/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryRequestString)
        ).andExpect(status().isOk());
    }

    @Test
    void shouldFindById() throws Exception {
        Category category = generateCategory();
        when(categoryService.findById(any())).thenReturn(category);

        mockMvc.perform(
            get("/api/category/1", 1L)
        ).andExpect(status().isOk());
    }

    @Test
    void shouldFindAll() throws Exception {
        List<Category> categories = Arrays.asList(generateCategory(), generateCategory());
        when(categoryService.findAll()).thenReturn(categories);

        mockMvc.perform(
            get("/api/category")
        ).andExpect(status().isOk());
    }

    @Test
    void shouldProperlyDelete() throws Exception {
        when(categoryService.delete(any())).thenReturn(true);

        mockMvc.perform(
            delete("/api/category/{categoryId}", 1L)
        ).andExpect(status().isOk());
    }

    Category generateCategory() {
        Category category = new Category();

        category.setId(1L);
        category.setColor("blue");
        category.setName("Blue");

        return category;
    }

    CategoryRequest generateCategoryRequest() {
        CategoryRequest categoryRequest = new CategoryRequest();

        categoryRequest.setColor("blud");
        categoryRequest.setName("Estudo");

        return categoryRequest;
    }
}