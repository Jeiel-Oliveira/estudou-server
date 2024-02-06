package com.estudou.scheduleservice.service;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.estudou.scheduleservice.dto.GoalRequest;
import com.estudou.scheduleservice.dto.ScheduleRequest;
import com.estudou.scheduleservice.dto.ScheduleVinculateGoalRequest;
import com.estudou.scheduleservice.event.ScheduleVinculateGoalEvent;
import com.estudou.scheduleservice.exception.GoalNotFoundException;
import com.estudou.scheduleservice.exception.ScheduleNotFoundException;
import com.estudou.scheduleservice.model.Schedule;
import com.estudou.scheduleservice.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;
  private final WebClient.Builder webClientBuilder;
  private final KafkaTemplate<String, ScheduleVinculateGoalEvent> kafkaTemplate;

  public Schedule create(ScheduleRequest scheduleRequest) {
    Schedule schedule = Schedule.builder()
      .studentId(scheduleRequest.getStudentId())
      .startDate(scheduleRequest.getStartDate())
      .endDate(scheduleRequest.getEndDate())
      .build();

    Schedule newSchedule = scheduleRepository.save(schedule);
    log.info("Schedule {} is saved", schedule.getId());

    return newSchedule;
  }

  public Schedule findById(String scheduleId) {
    Schedule schedule = scheduleRepository.findById(scheduleId)
      .orElseThrow(() -> new ScheduleNotFoundException(scheduleId));

    return schedule;
  }

  public boolean delete(String scheduleId) {
    scheduleRepository.deleteById(scheduleId);
    return true;
  }

  public List<Schedule> findAll() {
    List<Schedule> schedules = scheduleRepository.findAll();
    return schedules.stream().map(this::mapToScheduleResponse).toList();
  }

  public GoalRequest vinculateGoal(String scheduleId, ScheduleVinculateGoalRequest scheduleVinculateGoalRequest) {
    String goalId = scheduleVinculateGoalRequest.getGoalId();
    String goalEndpoint = "http://goal-service/api/goal/" + goalId;

    GoalRequest goal = webClientBuilder.build().get()
      .uri(goalEndpoint)
      .retrieve()
      .bodyToMono(GoalRequest.class)
      .block();

    if (goal == null) {
      throw new GoalNotFoundException(goalId);
    }

    Schedule schedule = scheduleRepository.findById(scheduleId)
      .orElseThrow(() -> new ScheduleNotFoundException(scheduleId));

    schedule.setGoalId(goalId);
    kafkaTemplate.send("notificationTopic", new ScheduleVinculateGoalEvent(goalId));
    scheduleRepository.save(schedule);

    return goal;
  }

  private Schedule mapToScheduleResponse(Schedule schedule) {
    return Schedule.builder()
      .id(schedule.getId())
      .studentId(schedule.getStudentId())
      .startDate(schedule.getStartDate())
      .endDate(schedule.getEndDate())
      .goalId(schedule.getGoalId())
      .build();
  }
}
