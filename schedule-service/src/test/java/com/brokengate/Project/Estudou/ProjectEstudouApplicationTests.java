package com.brokengate.Project.Estudou;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.brokengate.Project.Estudou.dto.ScheduleRequest;
import com.brokengate.Project.Estudou.dto.ScheduleVinculateGoalRequest;
import com.brokengate.Project.Estudou.model.Schedule;
import com.brokengate.Project.Estudou.repository.ScheduleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProjectEstudouApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.4.2"));

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
		dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateSchedule() throws Exception {
		ScheduleRequest scheduleRequest = getScheduleRequest();
		String scheduleRequestString = objectMapper.writeValueAsString(scheduleRequest);

		mockMvc.perform(
			MockMvcRequestBuilders.post("/api/schedule")
			.contentType(MediaType.APPLICATION_JSON)
			.content(scheduleRequestString))
			.andExpect(status().isCreated());

		Assertions.assertTrue(scheduleRepository.findAll().size() == 1);
	}

	@Test
	void shouldGetSchedule() throws Exception {
		ScheduleRequest scheduleRequest = getScheduleRequest();
		String scheduleRequestString = objectMapper.writeValueAsString(scheduleRequest);

		List<String> scheduleList = new ArrayList<String>();
		scheduleList.add(scheduleRequestString);

		String scheduleRequestListString = objectMapper.writeValueAsString(scheduleList);

		mockMvc.perform(
			MockMvcRequestBuilders.get("/api/schedule")
			.contentType(MediaType.APPLICATION_JSON)
			.content(scheduleRequestListString)
		).andExpect(status().isOk());
	}

	@Test
	void shouldVinculateGoal() throws Exception {
		Schedule schedule = new Schedule();
		ScheduleRequest scheduleRequest = getScheduleRequest();

		schedule.setStudentId(scheduleRequest.getStudentId());
		schedule.setStartDate(scheduleRequest.getStartDate());
		schedule.setEndDate(scheduleRequest.getEndDate());

		Schedule newSchedule = scheduleRepository.save(schedule);

		ScheduleVinculateGoalRequest scheduleVinculateGoalRequest = new ScheduleVinculateGoalRequest();
		scheduleVinculateGoalRequest.setGoalId("1");

		String scheduleVinculateGoalRequestString = objectMapper.writeValueAsString(scheduleVinculateGoalRequest);

		mockMvc.perform(
			MockMvcRequestBuilders.post(String.format("/api/schedule/%d/goals", newSchedule.getId()))
			.contentType(MediaType.APPLICATION_JSON)
			.content(scheduleVinculateGoalRequestString)
		).andExpect(status().isOk());
	}

	private ScheduleRequest getScheduleRequest() {
		return ScheduleRequest.builder()
			.studentId("1")
			.startDate("03-03-2023")
			.endDate("03-03-2023")
			.build();
	}
}
