package com.estudou.scheduleservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.estudou.scheduleservice.dto.ScheduleRequest;

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

  public static Schedule factoryScheduleRequest(ScheduleRequest scheduleRequest) {
    Schedule schedule = new Schedule();

    schedule.setStudentId(scheduleRequest.getStudentId());
    schedule.setStartDate(scheduleRequest.getStartDate());
    schedule.setEndDate(scheduleRequest.getEndDate());
    schedule.setGoalId(scheduleRequest.getGoalId());

    return schedule;
  }
}
