package com.brokengate.categoryservice.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.brokengate.categoryservice.dto.CategoryRequest;
import com.brokengate.categoryservice.model.Category;
import com.brokengate.categoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void create(CategoryRequest categoryRequest) {
        Category category = new Category();

        category.setName(categoryRequest.getName());
        category.setColor(categoryRequest.getColor());

        categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
