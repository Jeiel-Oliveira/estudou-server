package com.estudou.missionservice.initializer;

import org.springframework.stereotype.Component;

import com.estudou.missionservice.model.Mission;
import com.estudou.missionservice.repository.MissionRepository;

import org.springframework.boot.CommandLineRunner;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MissionInitializer implements CommandLineRunner {

    private final MissionRepository missionRepository;

    Mission pomodoro() {
        Mission mission = new Mission();

        mission.setName("Pomodoro");
        mission.setPoints(15);
        mission.setType(Mission.Type.POMODORO);

        return mission;
    }

    Mission platformEnter() {
        Mission mission = new Mission();

        mission.setName("Enter platform");
        mission.setPoints(5);
        mission.setType(Mission.Type.ENTER);

        return mission;
    }

    Mission editAnnotation() {
        Mission mission = new Mission();

        mission.setName("Add or edit an annotation");
        mission.setPoints(10);
        mission.setType(Mission.Type.ANNOTATION);

        return mission;
    }

    void savePomodoro() {
        Mission pomodoro = pomodoro();
        boolean exists = missionRepository.existsByType(pomodoro.getType());
        if (exists == false) {
            missionRepository.save(pomodoro());
        }
    }

    void saveEditAnnotation() {
        Mission annotation = editAnnotation();
        boolean exists = missionRepository.existsByType(annotation.getType());
        if (exists == false) {
            missionRepository.save(annotation);
        }
    }

    void savePlatformEnter() {
        Mission platformEnter = platformEnter();
        boolean exists = missionRepository.existsByType(platformEnter.getType());
        if (exists == false) {
            missionRepository.save(platformEnter);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        saveEditAnnotation();
        savePlatformEnter();
        savePomodoro();
    }
}
