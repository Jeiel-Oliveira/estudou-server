package com.estudou.scheduleservice.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.estudou.scheduleservice.advice.ResponseAdvice;
import com.estudou.scheduleservice.dto.ScheduleRequest;
import com.estudou.scheduleservice.dto.ScheduleVinculateGoalRequest;
import com.estudou.scheduleservice.exception.GoalServiceUnavailableException;
import com.estudou.scheduleservice.model.Schedule;
import com.estudou.scheduleservice.service.ScheduleService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleController {

  private final ScheduleService scheduleService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseAdvice<Schedule> create(@Valid @RequestBody ScheduleRequest scheduleRequest) {
    Schedule schedule = scheduleService.create(scheduleRequest);

    ResponseAdvice<Schedule> responseAdvice = new ResponseAdvice<Schedule>(
        HttpStatus.CREATED,
        "Schedule created successfully.",
        schedule
    );

    return responseAdvice;
  }

  @PatchMapping("/{scheduleId}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseAdvice<Schedule> update(@PathVariable(value="scheduleId") String scheduleId, @Valid @RequestBody ScheduleRequest scheduleRequest) {
    Schedule schedule = scheduleService.update(scheduleId, scheduleRequest);

    ResponseAdvice<Schedule> responseAdvice = new ResponseAdvice<Schedule>(
      HttpStatus.OK,
      "Schedule updated successfully.",
      schedule
    );

    return responseAdvice;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseAdvice<List<Schedule>> findAll() {

    List<Schedule> schedules = scheduleService.findAll();

    ResponseAdvice<List<Schedule>> responseAdvice = new ResponseAdvice<List<Schedule>>(
        HttpStatus.OK,
        "Schedules found successfully.",
        schedules
    );

    return responseAdvice;
  }

  @GetMapping("/{scheduleId}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseAdvice<Schedule> findById(@PathVariable(value="scheduleId") String scheduleId) {
    Schedule schedule = scheduleService.findById(scheduleId);

    ResponseAdvice<Schedule> responseAdvice = new ResponseAdvice<Schedule>(
        HttpStatus.OK,
        "Schedule found successfully.",
        schedule
    );

    return responseAdvice;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/{scheduleId}/goals")
  @CircuitBreaker(name="goal", fallbackMethod="fallbackGoal")
  @TimeLimiter(name="goal", fallbackMethod="fallbackGoal")
  @Retry(name="goal")
  public CompletableFuture<ResponseAdvice<Schedule>> vinculateGaol(@PathVariable(value="scheduleId") String scheduleId, @RequestBody ScheduleVinculateGoalRequest scheduleVinculateGoalRequest) {
    return CompletableFuture.supplyAsync(() -> {
      Schedule schedule = scheduleService.vinculateGoal(scheduleId, scheduleVinculateGoalRequest);

      ResponseAdvice<Schedule> responseAdvice = new ResponseAdvice<Schedule>(
        HttpStatus.OK,
        String.format("Schedule successfully vinculate with goal %s", scheduleVinculateGoalRequest.getGoalId()),
        schedule
      );

      return responseAdvice;
    });
  }

  @DeleteMapping("/{scheduleId}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseAdvice<String> delete(@PathVariable(value="scheduleId") String scheduleId) {
    scheduleService.delete(scheduleId);

    ResponseAdvice<String> responseAdvice = new ResponseAdvice<String>(
        HttpStatus.OK,
        String.format("Schedule %s successfully deleted", scheduleId),
        null
    );

    return responseAdvice;
  }

  public CompletableFuture<ResponseAdvice<Schedule>> fallbackGoal (
    String scheduleId,
    ScheduleVinculateGoalRequest scheduleVinculateGoalRequest,
    ResponseStatusException responseStatusException) throws ResponseStatusException {
    System.out.println(responseStatusException);
    throw new GoalServiceUnavailableException(responseStatusException.getReason());
  }
}
