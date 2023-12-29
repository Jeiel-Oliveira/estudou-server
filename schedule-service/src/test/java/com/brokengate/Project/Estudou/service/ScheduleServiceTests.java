package com.brokengate.Project.Estudou.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.brokengate.Project.Estudou.dto.ScheduleRequest;
import com.brokengate.Project.Estudou.model.Schedule;
import com.brokengate.Project.Estudou.repository.ScheduleRepository;

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
    void shouldFindById() {
        String scheduleId = "1";

        when(scheduleRepository.findById("1")).thenReturn(generateOptionalSchedule());
        Schedule schedule = scheduleService.findById(scheduleId);

        Assertions.assertInstanceOf(Schedule.class, schedule);
        Assertions.assertEquals(schedule.getStudentId(), "1");
    }

    ScheduleRequest generateScheduleRequest() {
        return ScheduleRequest.builder()
            .studentId("1")
            .startDate("03-03-2023")
            .endDate("03-03-2023")
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
