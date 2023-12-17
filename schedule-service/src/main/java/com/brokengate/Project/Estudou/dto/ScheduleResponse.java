package com.brokengate.Project.Estudou.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleResponse {
  private String id;
  private String studentId;
  private String startDate;
  private String endDate;
} 
