package com.estudou.goalsservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.estudou.goalsservice.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

}

