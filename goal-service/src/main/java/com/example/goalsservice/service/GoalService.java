package com.example.goalsservice.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.goalsservice.dto.GoalRequest;
import com.example.goalsservice.exception.GoalNotFoundException;
import com.example.goalsservice.model.Goal;
import com.example.goalsservice.repository.GoalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    public void create(GoalRequest goalRequest) {
        Goal goal = new Goal();

        goal.setColor(goalRequest.getColor());
        goal.setText(goalRequest.getText());
        goal.setDayId(goalRequest.getDayId());
        goal.setTitle(goalRequest.getTitle());

        goalRepository.save(goal);
    }

    public Optional<Goal> findById(Long goalId) {
        Optional<Goal> goal = goalRepository.findById(goalId);

        if (goal.isEmpty()) {
            throw new GoalNotFoundException(Long.toString(goalId));
        }

        return goal;
    }
}
