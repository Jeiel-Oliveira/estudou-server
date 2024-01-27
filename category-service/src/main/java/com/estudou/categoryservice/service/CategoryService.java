package com.estudou.categoryservice.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.estudou.categoryservice.dto.CategoryRequest;
import com.estudou.categoryservice.exception.CategoryNotFoundException;
import com.estudou.categoryservice.model.Category;
import com.estudou.categoryservice.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category create(CategoryRequest categoryRequest) {
        Category category = new Category();

        category.setName(categoryRequest.getName());
        category.setColor(categoryRequest.getColor());

        Category categoryRes = categoryRepository.save(category);
        return categoryRes;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Boolean delete(String categoryId) {
        findById(categoryId);
        categoryRepository.deleteById(Long.parseLong(categoryId));

        return true;
    }

    public Category findById(String categoryId) {
        Category category = categoryRepository.findById(Long.parseLong(categoryId))
            .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        return category;
    }
}
