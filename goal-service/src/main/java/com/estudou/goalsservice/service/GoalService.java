package com.estudou.goalsservice.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.estudou.goalsservice.dto.GoalRequest;
import com.estudou.goalsservice.exception.GoalNotFoundException;
import com.estudou.goalsservice.model.Goal;
import com.estudou.goalsservice.repository.GoalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    public Goal create(GoalRequest goalRequest) {
        Goal goal = Goal.factoryGoalRequest(goalRequest);
        return goalRepository.save(goal);
    }

    public Goal update(String goalId, GoalRequest goalRequest) {
        Long parsedGoalId = Long.parseLong(goalId);
        findById(parsedGoalId);

        Goal goal = Goal.factoryGoalRequest(goalRequest);
        goal.setId(parsedGoalId);

        return goalRepository.save(goal);
    }

    public List<Goal> findAll() {
        List<Goal> goals = goalRepository.findAll();
        return goals;
    }

    public boolean delete(String goalId) {
        Long parsedGoalId = Long.parseLong(goalId);
        findById(parsedGoalId);

        goalRepository.deleteById(parsedGoalId);
        return true;
    }

    public Goal findById(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
            .orElseThrow(() -> new GoalNotFoundException(Long.toString(goalId)));

        return goal;
    }
}
