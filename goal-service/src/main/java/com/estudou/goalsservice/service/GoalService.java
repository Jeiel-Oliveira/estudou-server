package com.estudou.goalsservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estudou.goalsservice.dto.GoalRequest;
import com.estudou.goalsservice.exception.GoalNotFoundException;
import com.estudou.goalsservice.model.Goal;
import com.estudou.goalsservice.repository.GoalRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing goal entities. This class provides methods for
 * creating, updating, retrieving, and deleting goals.
 */
@Service
@RequiredArgsConstructor
public class GoalService {

  private final GoalRepository goalRepository;

  /**
   * Creates a new goal with the provided goal request.
   *
   * @param goalRequest The goal request containing information about the goal to
   *                    be created.
   * @return The newly created goal.
   */
  public Goal create(GoalRequest goalRequest) {
    Goal goal = Goal.factoryGoalRequest(goalRequest);
    return goalRepository.save(goal);
  }

  /**
   * Updates an existing goal with the provided ID and goal request.
   *
   * @param goalId      The ID of the goal to be updated.
   * @param goalRequest The goal request containing updated information for the
   *                    goal.
   * @return The updated goal.
   */
  public Goal update(String goalId, GoalRequest goalRequest) {
    Long parsedGoalId = Long.parseLong(goalId);
    findById(parsedGoalId);

    Goal goal = Goal.factoryGoalRequest(goalRequest);
    goal.setId(parsedGoalId);

    return goalRepository.save(goal);
  }

  /**
   * Retrieves all goals.
   *
   * @return A list of all goals.
   */
  public List<Goal> findAll() {
    List<Goal> goals = goalRepository.findAll();
    return goals;
  }

  /**
   * Deletes a goal by its ID.
   *
   * @param goalId The ID of the goal to delete.
   * @return True if the goal was successfully deleted, false otherwise.
   */
  public boolean delete(String goalId) {
    Long parsedGoalId = Long.parseLong(goalId);
    findById(parsedGoalId);

    goalRepository.deleteById(parsedGoalId);
    return true;
  }

  /**
   * Retrieves a goal by its ID.
   *
   * @param goalId The ID of the goal to retrieve.
   * @return The goal with the specified ID.
   * @throws GoalNotFoundException If the goal with the specified ID is not found.
   */
  public Goal findById(Long goalId) {
    Goal goal = goalRepository.findById(goalId)
        .orElseThrow(() -> new GoalNotFoundException(Long.toString(goalId)));

    return goal;
  }
}
