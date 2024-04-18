package com.estudou.missionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudou.missionservice.model.Mission;

/**
 * Repository interface for managing mission entities. This interface extends
 * JpaRepository to inherit basic CRUD operations for missions.
 */
public interface MissionRepository extends JpaRepository<Mission, Long> {
  boolean existsByType(Mission.Type type);
}
