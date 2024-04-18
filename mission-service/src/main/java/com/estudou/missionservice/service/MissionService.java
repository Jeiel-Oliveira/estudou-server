package com.estudou.missionservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estudou.missionservice.dto.MissionRequest;
import com.estudou.missionservice.exception.MissionNotFoundException;
import com.estudou.missionservice.model.Mission;
import com.estudou.missionservice.repository.MissionRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing mission entities. This class provides methods for
 * creating, retrieving, and deleting missions.
 */
@Service
@RequiredArgsConstructor
public class MissionService {

  private final MissionRepository missionRepository;

  /**
   * Retrieves a mission by its ID.
   *
   * @param missionId The ID of the mission to retrieve.
   * @return The mission with the specified ID.
   * @throws MissionNotFoundException If the mission with the specified ID is not
   *                                  found.
   */
  public Mission findById(Long missionId) {
    Mission mission = missionRepository.findById(missionId)
        .orElseThrow(() -> new MissionNotFoundException(Long.toString(missionId)));

    return mission;
  }

  /**
   * Creates a new mission based on the provided mission request.
   *
   * @param missionRequest The mission request containing information about the
   *                       mission to create.
   * @return The newly created mission.
   */
  public Mission create(MissionRequest missionRequest) {
    Mission mission = new Mission();

    mission.setName(missionRequest.getName());
    mission.setPoints(missionRequest.getPoints());
    mission.setType(missionRequest.getType());

    return missionRepository.save(mission);
  }

  /**
   * Deletes a mission by its ID.
   *
   * @param missionId The ID of the mission to delete.
   */
  public void delete(String missionId) {
    Long longMissionId = Long.parseLong(missionId);
    findById(longMissionId);
    missionRepository.deleteById(longMissionId);
  }

  /**
   * Retrieves all missions.
   *
   * @return A list containing all missions.
   */
  public List<Mission> findAll() {
    List<Mission> missions = missionRepository.findAll();
    return missions;
  }
}
