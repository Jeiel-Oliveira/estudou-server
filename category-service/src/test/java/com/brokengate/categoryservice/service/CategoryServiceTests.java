package com.brokengate.categoryservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.brokengate.categoryservice.dto.CategoryRequest;
import com.brokengate.categoryservice.exception.CategoryNotFoundException;
import com.brokengate.categoryservice.model.Category;
import com.brokengate.categoryservice.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldCreate() {
        CategoryRequest categoryRequest = generateCategoryRequest();
        when(categoryRepository.save(any())).thenReturn(generateCategory());

        Category categoryRes = categoryService.create(categoryRequest);

        Assertions.assertEquals(categoryRes.getId(), 1L);
        Assertions.assertEquals(categoryRes.getColor(), "Blue");
        Assertions.assertEquals(categoryRes.getName(), "Estudo");
        Assertions.assertInstanceOf(Category.class, categoryRes);
    }

    @Test
    void shouldFindAll() {
        List<Category> categories = Arrays.asList(generateCategory(), generateCategory());
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> categoriesRes = categoryService.findAll();

        Assertions.assertEquals(categories.size(), categoriesRes.size());
        Assertions.assertInstanceOf(Category.class, categoriesRes.get(0));
    }

    @Test
    void shouldFindById() {
        String categoryId = "1";

        when(categoryRepository.findById(Long.parseLong(categoryId))).thenReturn(generateOptionalCategory());
        Category category = categoryService.findById(categoryId);

        Assertions.assertInstanceOf(Category.class, category);
        Assertions.assertEquals(category.getName(), "Estudo");
    }

    @Test
    void shouldthrowExceptionWhenDontFind() {
        String categoryId = "1";

        when(categoryRepository.findById(Long.parseLong(categoryId))).thenReturn(Optional.empty());

        Assertions.assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.findById(categoryId);
        });
    }

    private CategoryRequest generateCategoryRequest() {
        CategoryRequest category = new CategoryRequest();
        category.setName("Testando");
        category.setColor("Azul");

        return category;
    }

    private Optional<Category> generateOptionalCategory() {
        return Optional.ofNullable(generateCategory());
    }

    private Category generateCategory() {
        Category category = new Category();

        category.setId(1L);
        category.setColor("Blue");
        category.setName("Estudo");

        return category;
    }
}
