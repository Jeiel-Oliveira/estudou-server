package com.estudou.scheduleservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an event indicating the vinculation of a goal with a schedule.
 * This class encapsulates the data related to the vinculation of a goal with a
 * schedule.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleVinculateGoalEvent {
  private String goalId;
}
