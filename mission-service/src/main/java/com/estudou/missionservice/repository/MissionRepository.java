package com.estudou.missionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudou.missionservice.model.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    boolean existsByType(Mission.Type type);
}

