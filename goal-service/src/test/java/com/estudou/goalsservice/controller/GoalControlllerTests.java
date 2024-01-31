package com.estudou.goalsservice.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.estudou.goalsservice.dto.GoalRequest;
import com.estudou.goalsservice.model.Goal;
import com.estudou.goalsservice.service.GoalService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(GoalController.class)
public class GoalControlllerTests {

	@MockBean
    private GoalService goalService;

    @Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldCreate() throws Exception {
		GoalRequest goalRequest = getGoalRequest();
		String goalRequestString = objectMapper.writeValueAsString(goalRequest);

        when(goalService.create(any())).thenReturn(generateGoal());

		mockMvc.perform(
			post("/api/goal")
			.contentType(MediaType.APPLICATION_JSON)
			.content(goalRequestString))
			.andExpect(status().isCreated());
	}

	@Test
	void shouldFailWithoutRequiredFields() throws Exception {
		mockMvc.perform(
			post("/api/goal")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{}"))
			.andExpect(status().isBadRequest());
	}

	@Test
	void shouldFindById() throws Exception {
        when(goalService.findById(any())).thenReturn(generateGoal());

		mockMvc.perform(get("/api/goal/1")
		    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

		MvcResult result = mockMvc.perform(get("/api/goal/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

		String content = result.getResponse().getContentAsString();
		Assertions.assertNotNull(content);
	}

	@Test
	void shouldFindAll() throws Exception {
		List<Goal> missions = Arrays.asList(generateGoal(), generateGoal());
		when(goalService.findAll()).thenReturn(missions);

		mockMvc.perform(get("/api/goal"))
			.andExpect(status().isOk());
	}

	@Test
	void shouldProperlyDelete() throws Exception {
		when(goalService.delete(any())).thenReturn(true);

		mockMvc.perform(delete("/api/goal/1", 1L))
			.andExpect(status().isOk());
	}

	private GoalRequest getGoalRequest() {
		GoalRequest goalRequest = new GoalRequest();

		goalRequest.setColor("blue");
		goalRequest.setText("Concluir o curso");
		goalRequest.setTitle("Curso");
		goalRequest.setDayId("1");

		return goalRequest;
	}

    private Goal generateGoal() {
        Goal goal = new Goal();

        goal.setTitle("Estudar");
        goal.setColor("blue");
        goal.setText("Estudar");
        goal.setId(1L);

        return goal;
    }
}
