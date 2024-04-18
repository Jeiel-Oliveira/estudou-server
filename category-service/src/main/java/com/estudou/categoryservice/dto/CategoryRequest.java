package com.estudou.categoryservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A data transfer object (DTO) representing a request to create or update a category.
 * This class encapsulates information about a category, including its name and color.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
  @NotNull(message = "name is required")
  @NotBlank
  private String name;

  private String color;
}
