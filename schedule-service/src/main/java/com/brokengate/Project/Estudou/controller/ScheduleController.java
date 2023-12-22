package com.brokengate.Project.Estudou.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.reactive.function.client.WebClient;

import com.brokengate.Project.Estudou.dto.ScheduleRequest;
import com.brokengate.Project.Estudou.dto.ScheduleResponse;
import com.brokengate.Project.Estudou.dto.ScheduleVinculateGoalRequest;
import com.brokengate.Project.Estudou.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleController {

  private final ScheduleService scheduleService;  
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createSchedule(@RequestBody ScheduleRequest scheduleRequest) {

    // Check if the goal exist before create a schedule
    scheduleService.create(scheduleRequest);
  }
  
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ScheduleResponse> getAll() {
    return scheduleService.getAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/{scheduleId}/goals")
  public String vinculateGaol(@PathVariable(value="scheduleId") String scheduleId, @RequestBody ScheduleVinculateGoalRequest scheduleVinculateGoalRequest) {    
    scheduleService.vinculateGoal(scheduleId, scheduleVinculateGoalRequest);
    return "Objetivo vinculado com sucesso";
  }
}
