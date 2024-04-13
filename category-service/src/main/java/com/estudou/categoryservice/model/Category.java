package com.estudou.categoryservice.model;

import com.estudou.categoryservice.dto.CategoryRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String color;

    public static Category factoryCategoryRequest(CategoryRequest categoryRequest) {
        Category category = new Category();

        category.setName(categoryRequest.getName());
        category.setColor(categoryRequest.getColor());

        return category;
    }
}
