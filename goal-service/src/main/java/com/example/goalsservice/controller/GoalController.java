package com.example.goalsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.goalsservice.dto.GoalRequest;
import com.example.goalsservice.model.Goal;
import com.example.goalsservice.service.GoalService;

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
    public Goal create(@Valid @RequestBody GoalRequest goalRequest) {
        return goalService.create(goalRequest);
    }

    @GetMapping("/{goalId}")
    @ResponseStatus(HttpStatus.OK)
    public Goal findById(@PathVariable(value="goalId") String goalId) {
        return goalService.findById(Long.parseLong(goalId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Goal> findAll() {
        return goalService.findAll();
    }

    @DeleteMapping("/{goalId}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable(value="goalId") String goalId) {
        goalService.delete(goalId);
        return String.format("Goal %s was deleted", goalId);
    }

}