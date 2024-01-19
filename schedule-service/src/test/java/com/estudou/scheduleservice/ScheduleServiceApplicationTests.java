package com.estudou.scheduleservice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.estudou.scheduleservice.dto.ScheduleRequest;
import com.estudou.scheduleservice.repository.ScheduleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@Disabled("Integration test, slow")
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ScheduleServiceApplicationTests {

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

	private ScheduleRequest getScheduleRequest() {
		return ScheduleRequest.builder()
			.studentId("1")
			.startDate("03-03-2023")
			.endDate("03-03-2023")
			.build();
	}
}
