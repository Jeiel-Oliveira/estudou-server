package com.estudou.goalsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudou.goalsservice.model.Goal;

/**
 * Repository interface for managing goal entities. This interface extends
 * JpaRepository to inherit basic CRUD operations for goals.
 */
public interface GoalRepository extends JpaRepository<Goal, Long> {

}
