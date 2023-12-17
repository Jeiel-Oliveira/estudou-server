package com.example.goalsservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;

import com.example.goalsservice.dto.GoalRequest;
import com.example.goalsservice.repository.GoalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class GoalsServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

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
	void shouldCreateGoal() {
		GoalRequest goalRequest = getGoalRequest();
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
