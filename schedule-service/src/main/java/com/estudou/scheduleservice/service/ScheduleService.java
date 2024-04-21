package com.estudou.scheduleservice.service;

import java.util.List;
import java.util.Map;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.estudou.scheduleservice.advice.JsonReponseAdvice;
import com.estudou.scheduleservice.dto.GoalRequest;
import com.estudou.scheduleservice.dto.ScheduleRequest;
import com.estudou.scheduleservice.dto.ScheduleVinculateGoalRequest;
import com.estudou.scheduleservice.event.ScheduleVinculateGoalEvent;
import com.estudou.scheduleservice.exception.GenericResponseException;
import com.estudou.scheduleservice.exception.GoalNotFoundException;
import com.estudou.scheduleservice.exception.ScheduleNotFoundException;
import com.estudou.scheduleservice.model.Schedule;
import com.estudou.scheduleservice.repository.ScheduleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for managing schedules. This class provides methods to perform
 * CRUD operations on schedules and associate goals with schedules.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;
  private final WebClient.Builder webClientBuilder;
  private final KafkaTemplate<String, ScheduleVinculateGoalEvent> kafkaTemplate;

  /**
   * Creates a new schedule.
   *
   * @param scheduleRequest The request containing the data for creating the
   *                        schedule.
   * @return The created schedule.
   */
  public Schedule create(ScheduleRequest scheduleRequest) {
    Schedule schedule = Schedule.factoryScheduleRequest(scheduleRequest);
    Schedule scheduleResponse = scheduleRepository.save(schedule);

    log.info("Schedule {} is saved", schedule.getId());
    return scheduleResponse;
  }

  /**
   * Updates an existing schedule.
   *
   * @param scheduleId      The ID of the schedule to update.
   * @param scheduleRequest The request containing the updated data for the
   *                        schedule.
   * @return The updated schedule.
   */
  public Schedule update(String scheduleId, ScheduleRequest scheduleRequest) {
    findById(scheduleId);

    Schedule schedule = Schedule.factoryScheduleRequest(scheduleRequest);
    schedule.setId(scheduleId);

    Schedule scheduleResponse = scheduleRepository.save(schedule);
    return scheduleResponse;
  }

  /**
   * Finds a schedule by ID.
   *
   * @param scheduleId The ID of the schedule to find.
   * @return The found schedule.
   * @throws ScheduleNotFoundException If the schedule with the given ID is not
   *                                   found.
   */
  public Schedule findById(String scheduleId) {
    Schedule schedule = scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new ScheduleNotFoundException(scheduleId));

    return schedule;
  }

  /**
   * Deletes a schedule by ID.
   *
   * @param scheduleId The ID of the schedule to delete.
   * @return True if the schedule was successfully deleted, false otherwise.
   */
  public boolean delete(String scheduleId) {
    findById(scheduleId);
    scheduleRepository.deleteById(scheduleId);
    return true;
  }

  public List<Schedule> findAll() {
    List<Schedule> schedules = scheduleRepository.findAll();
    return schedules.stream().map(this::mapToScheduleResponse).toList();
  }

  /**
   * Associates a goal with a schedule.
   *
   * @param scheduleId                   The ID of the schedule to associate the
   *                                     goal with.
   * @param scheduleVinculateGoalRequest The request containing the ID of the goal
   *                                     to associate.
   * @return The updated schedule with the associated goal.
   * @throws GoalNotFoundException If the goal with the given ID is not found.
   */
  public Schedule vinculateGoal(String scheduleId,
      ScheduleVinculateGoalRequest scheduleVinculateGoalRequest) {
    String goalId = scheduleVinculateGoalRequest.getGoalId();

    Schedule schedule = findById(scheduleId);

    webClientBuilder.build().get().uri("http://goal-service/api/goal/" + goalId)
        .retrieve().bodyToMono(GoalRequest.class)
        .onErrorMap(WebClientResponseException.class, this::handleWebClientResponseException)
        .block();

    schedule.setGoalId(goalId);
    kafkaTemplate.send("notificationTopic", new ScheduleVinculateGoalEvent(goalId));
    scheduleRepository.save(schedule);

    return schedule;
  }

  /**
   * Handles WebClientResponseException by parsing the error response body and
   * converting it into a Throwable. This method is used in the onErrorMap
   * function of a WebClient Mono chain to handle errors from external services.
   *
   * @param error The WebClientResponseException thrown by the WebClient request.
   * @return A Throwable representing the error, either a GenericResponseException
   *         containing the parsed error message from the response body, or a
   *         GenericResponseException with the original error message if parsing
   *         fails.
   */
  @SuppressWarnings("unchecked")
  private Throwable handleWebClientResponseException(WebClientResponseException error) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonReponseAdvice<Map<String, Object>> bodyString = objectMapper
          .readValue(error.getResponseBodyAsString(), JsonReponseAdvice.class);

      return new GenericResponseException(error.getStatusCode(), bodyString.getMessage());
    } catch (JsonProcessingException e) {
      log.error("Error parsing JSON response from goal service", e);
      return new GenericResponseException(error.getStatusCode(), error.getMessage());
    }
  }

  /**
   * Maps a schedule entity to a response DTO.
   *
   * @param schedule The schedule entity to map.
   * @return The mapped schedule response DTO.
   */
  private Schedule mapToScheduleResponse(Schedule schedule) {
    return Schedule.builder().id(schedule.getId()).studentId(schedule.getStudentId())
        .startDate(schedule.getStartDate()).endDate(schedule.getEndDate())
        .goalId(schedule.getGoalId()).build();
  }

}
