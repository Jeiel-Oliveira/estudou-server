package com.estudou.categoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estudou.categoryservice.dto.CategoryRequest;
import com.estudou.categoryservice.exception.CategoryNotFoundException;
import com.estudou.categoryservice.model.Category;
import com.estudou.categoryservice.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing category entities. This class provides methods for
 * performing CRUD operations on categories and handling business logic related
 * to categories.
 */
@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  /**
   * Creates a new category with the provided category request.
   *
   * @param categoryRequest The category request containing information about the
   *                        category to be created.
   * @return The newly created category.
   */
  public Category create(CategoryRequest categoryRequest) {
    Category category = Category.factoryCategoryRequest(categoryRequest);
    return categoryRepository.save(category);
  }

  /**
   * Updates an existing category with the provided ID and category request.
   *
   * @param categoryId      The ID of the category to be updated.
   * @param categoryRequest The category request containing updated information
   *                        for the category.
   * @return The updated category.
   * @throws CategoryNotFoundException If the category with the specified ID is
   *                                   not found.
   */
  public Category update(String categoryId, CategoryRequest categoryRequest) {
    Long parsedCategoryId = Long.parseLong(categoryId);
    findById(categoryId);

    Category category = Category.factoryCategoryRequest(categoryRequest);
    category.setId(parsedCategoryId);

    return categoryRepository.save(category);
  }

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public Category findByName(String name) {
    return categoryRepository.findByName(name);
  }

  /**
   * Deletes a category by its ID.
   *
   * @param categoryId The ID of the category to delete.
   * @return True if the category was successfully deleted, false otherwise.
   * @throws CategoryNotFoundException If the category with the specified ID is
   *                                   not found.
   */
  public Boolean delete(String categoryId) {
    findById(categoryId);
    categoryRepository.deleteById(Long.parseLong(categoryId));

    return true;
  }

  /**
   * Retrieves a category by its ID.
   *
   * @param categoryId The ID of the category to retrieve.
   * @return The category with the specified ID.
   * @throws CategoryNotFoundException If the category with the specified ID is
   *                                   not found.
   */
  public Category findById(String categoryId) {
    Category category = categoryRepository.findById(Long.parseLong(categoryId))
        .orElseThrow(() -> new CategoryNotFoundException(categoryId));

    return category;
  }
}
