package com.estudou.scheduleservice.dto;

import lombok.Data;

/**
 * Represents a request to associate a goal with a schedule. This class
 * encapsulates the data required to associate a goal with a schedule.
 */
@Data
public class ScheduleVinculateGoalRequest {
  private String goalId;
}
