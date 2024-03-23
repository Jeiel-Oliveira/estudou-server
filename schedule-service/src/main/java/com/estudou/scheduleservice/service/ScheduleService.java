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

  public Schedule vinculateGoal(String scheduleId, ScheduleVinculateGoalRequest scheduleVinculateGoalRequest) {
    String goalId = scheduleVinculateGoalRequest.getGoalId();

    GoalRequest goal = webClientBuilder.build()
      .get()
      .uri("http://goal-service/api/goal/" + goalId)
      .retrieve()
      .bodyToMono(GoalRequest.class)
      // [TODO] Is necessary to map another possibles errors in addition to GoalNotFoundException
      .onErrorMap(error -> new GoalNotFoundException(goalId))
      .block();

    log.info("goal finded", goal);

    Schedule schedule = scheduleRepository.findById(scheduleId)
      .orElseThrow(() -> new ScheduleNotFoundException(scheduleId));

    schedule.setGoalId(goal.getId());
    kafkaTemplate.send("notificationTopic", new ScheduleVinculateGoalEvent(goalId));
    scheduleRepository.save(schedule);

    return schedule;
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
