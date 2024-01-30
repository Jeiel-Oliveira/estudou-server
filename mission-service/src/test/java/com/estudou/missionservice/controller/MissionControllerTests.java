package com.estudou.missionservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.estudou.missionservice.model.Mission;
import com.estudou.missionservice.service.MissionService;

@WebMvcTest(MissionController.class)
public class MissionControllerTests {

    @MockBean
    private MissionService missionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFindById() throws Exception {
        Mission mission = generateMission();
        when(missionService.findById(any())).thenReturn(mission);

        mockMvc.perform(get("/api/mission/1", 1L))
            .andExpect(status().isOk());
    }

    @Test
    void shouldFindAll() throws Exception {
        List<Mission> missions = Arrays.asList(generateMission(), generateMission());
        when(missionService.findAll()).thenReturn(missions);

        mockMvc.perform(get("/api/mission"))
            .andExpect(status().isOk());
    }

    private Mission generateMission() {
        Mission mission = new Mission();

        mission.setId(1L);
        mission.setName("Pomodoro");
        mission.setPoints(30);
        mission.setType(Mission.Type.POMODORO);

        return mission;
    }
}
