package com.example.goalsservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;

import com.example.goalsservice.dto.GoalRequest;
import com.example.goalsservice.model.Goal;
import com.example.goalsservice.repository.GoalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class GoalsServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private GoalRepository goalRepository;

	static final MySQLContainer<?> mysqlContainer;

	static {
		mysqlContainer = new MySQLContainer<>("mysql:8.0.35");
		mysqlContainer.start();
	}

	@DynamicPropertySource
	static void configureTestProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mysqlContainer::getUsername);
		registry.add("spring.datasource.password", mysqlContainer::getPassword);
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
	}

	@Test
	void shouldCreateGoal() throws Exception {
		GoalRequest goalRequest = getGoalRequest();
		String goalRequestString = objectMapper.writeValueAsString(goalRequest);
		
		mockMvc.perform(
			MockMvcRequestBuilders.post("/api/goal")
			.contentType(MediaType.APPLICATION_JSON)
			.content(goalRequestString))
			.andExpect(status().isCreated());

		Assertions.assertTrue(goalRepository.findAll().size() == 1);
	}

	@Test
	void shouldFindOneGoalById() throws Exception {
		Goal goal = new Goal();

		goal.setColor("blue");
		goal.setText("Concluir o curso");
		goal.setTitle("Curso");
		goal.setDayId("1");		
		goal.setId(1L);

		goalRepository.save(goal);

		mockMvc.perform(
			MockMvcRequestBuilders.get("/api/goal/1")
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk());

		MvcResult result = mockMvc.perform(
			MockMvcRequestBuilders.get("/api/goal/1")
				.contentType(MediaType.APPLICATION_JSON)					
		).andReturn();

		String content = result.getResponse().getContentAsString();
		Assertions.assertNotNull(content);
	}

	private GoalRequest getGoalRequest() {
		GoalRequest goalRequest = new GoalRequest();

		goalRequest.setColor("blue");
		goalRequest.setText("Concluir o curso");
		goalRequest.setTitle("Curso");
		goalRequest.setDayId("1");

		return goalRequest;
	}
}
