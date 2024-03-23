package com.estudou.scheduleservice.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
  public Schedule create(@Valid @RequestBody ScheduleRequest scheduleRequest) {
    // Check if the goal exist before create a schedule
    return scheduleService.create(scheduleRequest);
  }

  @DeleteMapping("/{scheduleId}")
  @ResponseStatus(HttpStatus.OK)
  public String delete(@PathVariable(value="scheduleId") String scheduleId) {
    scheduleService.delete(scheduleId);
    return String.format("Schedule %s successfully deleted", scheduleId);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Schedule> findAll() {
    return scheduleService.findAll();
  }

  @GetMapping("/{scheduleId}")
  @ResponseStatus(HttpStatus.OK)
  public Schedule findById(@PathVariable(value="scheduleId") String scheduleId) {
    return scheduleService.findById(scheduleId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/{scheduleId}/goals")
  @CircuitBreaker(name="goal", fallbackMethod="fallbackGoal")
  @TimeLimiter(name="goal", fallbackMethod="fallbackGoal")
  @Retry(name="goal")
  public CompletableFuture<Schedule> vinculateGaol(@PathVariable(value="scheduleId") String scheduleId, @RequestBody ScheduleVinculateGoalRequest scheduleVinculateGoalRequest) {
    return CompletableFuture.supplyAsync(() -> scheduleService.vinculateGoal(scheduleId, scheduleVinculateGoalRequest)) ;
  }

  public CompletableFuture<Schedule> fallbackGoal (
    String scheduleId,
    ScheduleVinculateGoalRequest scheduleVinculateGoalRequest,
    ResponseStatusException responseStatusException) throws ResponseStatusException {
    System.out.println(responseStatusException);
    throw new GoalServiceUnavailableException(responseStatusException.getReason());
  }
}
