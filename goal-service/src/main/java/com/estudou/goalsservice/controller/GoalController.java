package com.estudou.goalsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.estudou.goalsservice.advice.ResponseAdvice;
import com.estudou.goalsservice.dto.GoalRequest;
import com.estudou.goalsservice.model.Goal;
import com.estudou.goalsservice.service.GoalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/goal")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseAdvice<Goal> create(@Valid @RequestBody GoalRequest goalRequest) {
        Goal goal = goalService.create(goalRequest);

        ResponseAdvice<Goal> responseAdvice = new ResponseAdvice<Goal>(
            HttpStatus.CREATED,
            "Goal created successfully.",
            goal
        );

        return responseAdvice;
    }

    @PatchMapping("/{goalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseAdvice<Goal> update(@PathVariable(value="goalId") String goalId, @Valid @RequestBody GoalRequest goalRequest) {
        Goal goal = goalService.update(goalId, goalRequest);

        ResponseAdvice<Goal> responseAdvice = new ResponseAdvice<Goal>(
            HttpStatus.OK,
            "Goal updated successfully.",
            goal
        );

        return responseAdvice;
    }

    @GetMapping("/{goalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseAdvice<Goal> findById(@PathVariable(value="goalId") String goalId) {
        Goal goal = goalService.findById(Long.parseLong(goalId));

        ResponseAdvice<Goal> responseAdvice = new ResponseAdvice<Goal>(
            HttpStatus.OK,
            "Goal found successfully.",
            goal
        );

        return responseAdvice;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseAdvice<List<Goal>> findAll() {
        List<Goal> goals = goalService.findAll();

        ResponseAdvice<List<Goal>> responseAdvice = new ResponseAdvice<List<Goal>>(
            HttpStatus.OK,
            "Goals found successfully.",
            goals
        );

        return responseAdvice;
    }

    @DeleteMapping("/{goalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseAdvice<String> delete(@PathVariable(value="goalId") String goalId) {
        goalService.delete(goalId);

        ResponseAdvice<String> responseAdvice = new ResponseAdvice<String>(
            HttpStatus.OK,
            String.format("Goal %s deleted successfully.", goalId),
            null
        );

        return responseAdvice;
    }

}