package com.brokengate.Project.Estudou.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.brokengate.Project.Estudou.dto.ScheduleRequest;
import com.brokengate.Project.Estudou.dto.ScheduleResponse;
import com.brokengate.Project.Estudou.model.Schedule;
import com.brokengate.Project.Estudou.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;

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

  private ScheduleResponse mapToScheduleResponse(Schedule schedule) {
    return ScheduleResponse.builder()
      .studentId(schedule.getStudentId())
      .startDate(schedule.getStartDate())
      .endDate(schedule.getEndDate())
      .build();
  }
}
