package com.estudou.categoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudou.categoryservice.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
