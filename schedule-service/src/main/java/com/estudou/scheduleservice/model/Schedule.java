package com.estudou.scheduleservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "schedules")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Schedule {
  @Id
  private String id;

  private String studentId;
  private String startDate;
  private String endDate;
  private String goalId;
}
