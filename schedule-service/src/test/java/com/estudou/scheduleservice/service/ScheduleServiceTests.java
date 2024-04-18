package com.estudou.scheduleservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.estudou.scheduleservice.dto.ScheduleRequest;
import com.estudou.scheduleservice.exception.ScheduleNotFoundException;
import com.estudou.scheduleservice.model.Schedule;
import com.estudou.scheduleservice.repository.ScheduleRepository;

/**
 * Tests for ScheduleService.
 */
@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTests {

  @Mock
  private ScheduleRepository scheduleRepository;

  @InjectMocks
  private ScheduleService scheduleService;

  @Test
  void shouldCreate() {
    ScheduleRequest scheduleRequest = generateScheduleRequest();
    when(scheduleRepository.save(any())).thenReturn(generateSchedule());

    Schedule schedule = scheduleService.create(scheduleRequest);

    Assertions.assertEquals(schedule.getStudentId(), "1");
    Assertions.assertEquals(schedule.getStartDate(), "03-03-2023");
    Assertions.assertInstanceOf(Schedule.class, schedule);
  }

  @Test
  void shouldUpdate() {
    String scheduleId = "1";

    Schedule updatedSchedule = generateSchedule();
    updatedSchedule.setId(scheduleId);
    updatedSchedule.setGoalId("1");
    updatedSchedule.setStudentId("2");

    when(scheduleRepository.save(any())).thenReturn(updatedSchedule);
    when(scheduleRepository.findById(scheduleId)).thenReturn(generateOptionalSchedule());

    ScheduleRequest scheduleRequest = generateScheduleRequest();
    Schedule scheduleResponse = scheduleService.update(scheduleId, scheduleRequest);

    Assertions.assertEquals(scheduleResponse.getGoalId(), "1");
    Assertions.assertEquals(scheduleResponse.getStudentId(), "2");
    Assertions.assertEquals(scheduleResponse.getId(), "1");

    Assertions.assertInstanceOf(Schedule.class, scheduleResponse);
    verify(scheduleRepository, times(1)).save(any());
  }

  @Test
  void shouldFindAll() {
    List<Schedule> schedules = Arrays.asList(generateSchedule(), generateSchedule());
    when(scheduleRepository.findAll()).thenReturn(schedules);

    List<Schedule> schedulesRes = scheduleService.findAll();

    Assertions.assertEquals(schedules.size(), schedulesRes.size());
    Assertions.assertInstanceOf(Schedule.class, schedulesRes.get(0));
  }

  @Test
  void shouldFindById() {
    String scheduleId = "1";

    when(scheduleRepository.findById("1")).thenReturn(generateOptionalSchedule());
    Schedule schedule = scheduleService.findById(scheduleId);

    Assertions.assertInstanceOf(Schedule.class, schedule);
    Assertions.assertEquals(schedule.getStudentId(), "1");
  }

  @Test
  void shouldThrowExceptionWhenDontFind() {
    String scheduleId = "1";
    when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.empty());

    Assertions.assertThrows(ScheduleNotFoundException.class,
        () -> scheduleService.findById(scheduleId));
  }

  ScheduleRequest generateScheduleRequest() {
    return ScheduleRequest.builder().studentId("1").startDate("03-03-2023").endDate("03-03-2023")
        .build();
  }

  Schedule generateSchedule() {
    Schedule schedule = new Schedule();

    schedule.setStudentId("1");
    schedule.setStartDate("03-03-2023");
    schedule.setEndDate("03-03-2023");

    return schedule;
  }

  Optional<Schedule> generateOptionalSchedule() {
    return Optional.ofNullable(generateSchedule());
  }
}
