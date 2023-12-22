package com.example.goalsservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/goal")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody @Valid GoalRequest goalRequest) {
        goalService.create(goalRequest);
        return "Objetivo criado com sucesso";
    }

    @GetMapping("/{goalId}")
    @ResponseStatus(HttpStatus.OK)
    public Goal findById(@PathVariable(value="goalId") String goalId) {
        Goal goal = goalService.findById(Long.parseLong(goalId));
        return goal;
    }
    
}