package com.example.missionservice.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.missionservice.dto.MissionRequest;
import com.example.missionservice.exception.MissionNotFoundException;
import com.example.missionservice.model.Mission;
import com.example.missionservice.repository.MissionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public Mission findById(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new MissionNotFoundException(Long.toString(missionId)));

        return mission;
    }

    public Mission create(MissionRequest missionRequest) {
        Mission mission = new Mission();

        mission.setName(missionRequest.getName());
        mission.setPoints(missionRequest.getPoints());
        mission.setType(missionRequest.getType());

        return missionRepository.save(mission);
    }

    public void delete(String missionId) {
        Long longMissionId = Long.parseLong(missionId);
        findById(longMissionId);
        missionRepository.deleteById(longMissionId);
    }

    public List<Mission> findAll() {
        List<Mission> missions = missionRepository.findAll();
        return missions;
    }
}
