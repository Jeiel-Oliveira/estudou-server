package com.brokengate.categoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brokengate.categoryservice.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> { 
    Category findByName(String name);
}
