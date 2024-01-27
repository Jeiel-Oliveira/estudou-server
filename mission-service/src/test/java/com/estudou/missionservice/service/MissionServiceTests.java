package com.estudou.missionservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.estudou.missionservice.dto.MissionRequest;
import com.estudou.missionservice.exception.MissionNotFoundException;
import com.estudou.missionservice.model.Mission;
import com.estudou.missionservice.repository.MissionRepository;

@ExtendWith(MockitoExtension.class)
public class MissionServiceTests {

    @Mock
    private MissionRepository missionRepository;

    @InjectMocks
    private MissionService missionService;

    @Test
    void shouldCreate() {
        MissionRequest missionRequest = generateMissionRequest();
        when(missionRepository.save(any())).thenReturn(generateMission());

        Mission mission = missionService.create(missionRequest);

        Assertions.assertEquals(mission.getName(), "Pomodoro");
        Assertions.assertEquals(mission.getPoints(), 30);
        Assertions.assertInstanceOf(Mission.class, mission);
    }

    @Test
    void shouldFindById() {
        Long missionId = 1L;

        when(missionRepository.findById(missionId)).thenReturn(generateOptionalMission());
        Mission mission = missionService.findById(missionId);

        Assertions.assertInstanceOf(Mission.class, mission);
        Assertions.assertEquals(mission.getId(), 1);
    }

    @Test
    void shouldThrowWhenNotFindingById() {
        Long parsedMissionId = 1L;

        when(missionRepository.findById(parsedMissionId)).thenReturn(Optional.empty());

        Assertions.assertThrows(MissionNotFoundException.class, () ->
            missionService.findById(parsedMissionId)
        );
    }

    @Test
    void shouldThrowWhenDeletingNoValue() {
        String missionId = "1";
        Long parsedMissionId = 1L;

        when(missionRepository.findById(parsedMissionId)).thenReturn(Optional.empty());

        Assertions.assertThrows(MissionNotFoundException.class, () ->
            missionService.delete(missionId)
        );
    }

    @Test
    void shouldFindAll() {
        List<Mission> missions = Arrays.asList(generateMission(), generateMission());
        when(missionRepository.findAll()).thenReturn(missions);

        List<Mission> missionRes = missionService.findAll();

        Assertions.assertEquals(missions.size(), missionRes.size());
        Assertions.assertTrue(missionRes.containsAll(missions));
        Assertions.assertInstanceOf(Mission.class, missionRes.get(1));
    }

	private MissionRequest generateMissionRequest() {
		MissionRequest missionRequest = new MissionRequest();

		missionRequest.setName("Pomodoro");
        missionRequest.setPoints(30);
        missionRequest.setType(Mission.Type.POMODORO);

		return missionRequest;
	}

    private Mission generateMission() {
        Mission mission = new Mission();

        mission.setId(1L);
        mission.setName("Pomodoro");
        mission.setPoints(30);
        mission.setType(Mission.Type.POMODORO);

        return mission;
    }

    private Optional<Mission> generateOptionalMission() {
        return Optional.ofNullable(generateMission());
    }
}
