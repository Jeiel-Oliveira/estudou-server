package com.example.goalsservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.goalsservice.dto.GoalRequest;
import com.example.goalsservice.service.GoalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/goal")
public class GoalController {
    private GoalService goalService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody GoalRequest goalRequest) {
        goalService.create(goalRequest);        
        return "Objetivo criado com sucesso";
    }
}
