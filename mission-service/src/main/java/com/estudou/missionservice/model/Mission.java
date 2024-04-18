package com.estudou.missionservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a mission. This class maps to the "t_mission" table
 * in the database and encapsulates information about a mission, including its
 * ID, type, name, and points.
 */
@Entity
@Table(name = "t_mission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mission {

  /**
   * Enumeration representing the type of mission. Possible values are POMODORO,
   * ENTER, and ANNOTATION.
   */
  public enum Type {
    POMODORO, ENTER, ANNOTATION
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Type type;

  private String name;
  private Integer points;
}
