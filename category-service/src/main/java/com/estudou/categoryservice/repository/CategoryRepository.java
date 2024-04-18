package com.estudou.categoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudou.categoryservice.model.Category;

/**
 * Repository interface for managing category entities.
 * This interface extends JpaRepository to inherit basic CRUD operations for categories.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
  Category findByName(String name);
}
