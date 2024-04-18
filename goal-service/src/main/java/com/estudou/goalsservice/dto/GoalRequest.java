package com.estudou.goalsservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO representing a request to create or update a
 * goal. This class encapsulates information about a goal, including its title,
 * text, and color.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalRequest {
  @NotBlank
  private String title;

  private String text;
  private String color;
}
