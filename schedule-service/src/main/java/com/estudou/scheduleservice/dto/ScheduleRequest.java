
package com.estudou.scheduleservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to create or update a schedule. This class encapsulates
 * the data required to create or update a schedule.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {
  private String studentId;

  @NotNull(message = "startDate is required")
  private String startDate;

  @NotNull(message = "endDate is required")
  private String endDate;

  private String goalId;
}
