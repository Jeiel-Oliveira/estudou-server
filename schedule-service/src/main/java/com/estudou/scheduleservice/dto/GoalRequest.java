package com.estudou.scheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to create or update a goal. This class encapsulates the
 * data required to create or update a goal.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalRequest {
  private String id;
  private String dayId;
  private String title;
  private String text;
  private String color;
}
