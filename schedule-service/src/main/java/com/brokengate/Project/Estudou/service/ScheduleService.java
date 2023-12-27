package com.brokengate.Project.Estudou.service;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.brokengate.Project.Estudou.dto.GoalRequest;
import com.brokengate.Project.Estudou.dto.ScheduleRequest;
import com.brokengate.Project.Estudou.dto.ScheduleResponse;
import com.brokengate.Project.Estudou.dto.ScheduleVinculateGoalRequest;
import com.brokengate.Project.Estudou.exception.GoalNotFoundException;
import com.brokengate.Project.Estudou.exception.ScheduleNotFoundException;
import com.brokengate.Project.Estudou.model.Schedule;
import com.brokengate.Project.Estudou.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;
  private final WebClient webClient;

  public void create(ScheduleRequest scheduleRequest) {
    Schedule schedule = Schedule.builder()
      .studentId(scheduleRequest.getStudentId())
      .startDate(scheduleRequest.getStartDate())
      .endDate(scheduleRequest.getEndDate())
      .build();

    scheduleRepository.save(schedule);
    log.info("Schedule {} is saved", schedule.getId());
  }

  public List<ScheduleResponse> getAll() {
    List<Schedule> schedules = scheduleRepository.findAll();
    return schedules.stream().map(this::mapToScheduleResponse).toList();
  }

  public GoalRequest vinculateGoal(String scheduleId, ScheduleVinculateGoalRequest scheduleVinculateGoalRequest) {
    String goalEndpoint = "http://localhost:8082/api/goal/" + scheduleVinculateGoalRequest.getGoalId();

    GoalRequest goal = webClient.get()
      .uri(goalEndpoint)
      .retrieve()
      .bodyToMono(GoalRequest.class)
      .block();

    if (goal == null) {
      throw new GoalNotFoundException(scheduleVinculateGoalRequest.getGoalId());
    }

    Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
    Schedule schedule = scheduleOptional.orElseThrow(() -> new ScheduleNotFoundException(scheduleId));

    schedule.setGoalId(scheduleVinculateGoalRequest.getGoalId());
    scheduleRepository.save(schedule);

    return goal;
  }

  private ScheduleResponse mapToScheduleResponse(Schedule schedule) {
    return ScheduleResponse.builder()
      .studentId(schedule.getStudentId())
      .startDate(schedule.getStartDate())
      .endDate(schedule.getEndDate())
      .build();
  }
}
