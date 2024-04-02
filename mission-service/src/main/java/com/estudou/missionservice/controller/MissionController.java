package com.estudou.missionservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estudou.missionservice.advice.ResponseAdvice;
import com.estudou.missionservice.model.Mission;
import com.estudou.missionservice.service.MissionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mission")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @GetMapping("/{missionId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseAdvice<Mission> findById(@PathVariable(value="missionId") String missionId) {
        Mission mission = missionService.findById(Long.parseLong(missionId));

        ResponseAdvice<Mission> responseAdvice = new ResponseAdvice<Mission>(
            HttpStatus.OK,
            "Mission found successfully.",
            mission
        );

        return responseAdvice;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseAdvice<List<Mission>> findAll() {
        List<Mission> missions = missionService.findAll();

        ResponseAdvice<List<Mission>> responseAdvice = new ResponseAdvice<List<Mission>>(
            HttpStatus.OK,
            "Missions found successfully.",
            missions
        );

        return responseAdvice;
    }

}