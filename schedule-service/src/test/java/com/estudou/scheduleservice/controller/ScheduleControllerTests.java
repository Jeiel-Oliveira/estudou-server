package com.estudou.scheduleservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.estudou.scheduleservice.dto.ScheduleRequest;
import com.estudou.scheduleservice.model.Schedule;
import com.estudou.scheduleservice.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ScheduleController.class)
public class ScheduleControllerTests {

    @MockBean
    private ScheduleService scheduleService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreate() throws Exception {
        ScheduleRequest scheduleRequest = generateScheduleRequest();
        String scheduleRequestToString = objectMapper.writeValueAsString(scheduleRequest);

        when(scheduleService.create(any())).thenReturn(generateSchedule());

        mockMvc.perform(
            post("/api/schedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(scheduleRequestToString)
        ).andExpect(status().isCreated());
    }

    @Test
    void shouldFailCreateWithoutBody() throws Exception {
        mockMvc.perform(
            post("/api/schedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdate() throws Exception {
        ScheduleRequest scheduleRequest = generateScheduleRequest();
        String scheduleRequestString = objectMapper.writeValueAsString(scheduleRequest);

        mockMvc.perform(
            patch("/api/schedule/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(scheduleRequestString)
        ).andExpect(status().isOk());
    }

    @Test
    void shouldFindById() throws Exception {
        Schedule schedule = generateSchedule();
        when(scheduleService.findById(any())).thenReturn(schedule);

        mockMvc.perform(
            get("/api/schedule/1", 1L)
        ).andExpect(status().isOk());
    }

    @Test
    void shouldFindAll() throws Exception {
        List<Schedule> schedules = Arrays.asList(generateSchedule(), generateSchedule());
        when(scheduleService.findAll()).thenReturn(schedules);

        mockMvc.perform(
            get("/api/schedule")
        ).andExpect(status().isOk());
    }

    @Test
    void shouldProperlyDelete() throws Exception {
        when(scheduleService.delete(any())).thenReturn(true);

        mockMvc.perform(
            delete("/api/schedule/1", 1L)
        ).andExpect(status().isOk());
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
