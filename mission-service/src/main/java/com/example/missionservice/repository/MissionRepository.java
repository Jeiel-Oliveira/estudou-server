package com.example.missionservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.missionservice.model.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {

}

