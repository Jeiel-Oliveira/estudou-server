package com.estudou.scheduleservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.estudou.scheduleservice.dto.ScheduleRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a schedule entity. This class encapsulates information about a
 * schedule.
 */
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

  /**
   * Factory method to create a Schedule object from a ScheduleRequest object.
   *
   * @param scheduleRequest The ScheduleRequest object containing the data to
   *                        create the Schedule.
   * @return The Schedule object created from the ScheduleRequest.
   */
  public static Schedule factoryScheduleRequest(ScheduleRequest scheduleRequest) {
    Schedule schedule = new Schedule();

    schedule.setStudentId(scheduleRequest.getStudentId());
    schedule.setStartDate(scheduleRequest.getStartDate());
    schedule.setEndDate(scheduleRequest.getEndDate());
    schedule.setGoalId(scheduleRequest.getGoalId());

    return schedule;
  }
}
