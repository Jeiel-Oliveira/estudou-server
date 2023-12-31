package com.brokengate.Project.Estudou.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brokengate.Project.Estudou.dto.GoalRequest;
import com.brokengate.Project.Estudou.dto.ScheduleRequest;
import com.brokengate.Project.Estudou.dto.ScheduleResponse;
import com.brokengate.Project.Estudou.dto.ScheduleVinculateGoalRequest;
import com.brokengate.Project.Estudou.model.Schedule;
import com.brokengate.Project.Estudou.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleController {

  private final ScheduleService scheduleService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Schedule createSchedule(@RequestBody ScheduleRequest scheduleRequest) {
    // Check if the goal exist before create a schedule
    return scheduleService.create(scheduleRequest);
  }

  @DeleteMapping("/{scheduleId}")
  @ResponseStatus(HttpStatus.OK)
  public String delete(@PathVariable(value="scheduleId") String scheduleId) {
    scheduleService.delete(scheduleId);
    return String.format("Schedule %s successfully deleted", scheduleId);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ScheduleResponse> getAll() {
    return scheduleService.getAll();
  }

  @GetMapping("/{scheduleId}")
  @ResponseStatus(HttpStatus.OK)
  public Schedule findById(@PathVariable(value="scheduleId") String scheduleId) {
    return scheduleService.findById(scheduleId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/{scheduleId}/goals")
  public GoalRequest vinculateGaol(@PathVariable(value="scheduleId") String scheduleId, @RequestBody ScheduleVinculateGoalRequest scheduleVinculateGoalRequest) {
    return scheduleService.vinculateGoal(scheduleId, scheduleVinculateGoalRequest);
  }
}
