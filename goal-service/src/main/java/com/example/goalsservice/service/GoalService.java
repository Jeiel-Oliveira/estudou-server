package com.example.goalsservice.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.goalsservice.dto.GoalRequest;
import com.example.goalsservice.exception.GoalNotFoundException;
import com.example.goalsservice.model.Goal;
import com.example.goalsservice.repository.GoalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    public Goal create(GoalRequest goalRequest) {
        Goal goal = new Goal();

        goal.setColor(goalRequest.getColor());
        goal.setText(goalRequest.getText());
        goal.setDayId(goalRequest.getDayId());
        goal.setTitle(goalRequest.getTitle());

        return goalRepository.save(goal);
    }

    public List<Goal> findAll() {
        List<Goal> goals = goalRepository.findAll();
        return goals;
    }

    public void delete(String goalId) {
        Long parsedGoalId = Long.parseLong(goalId);
        findById(parsedGoalId);

        goalRepository.deleteById(parsedGoalId);
    }

    public Goal findById(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
            .orElseThrow(() -> new GoalNotFoundException(Long.toString(goalId)));

        return goal;
    }
}
