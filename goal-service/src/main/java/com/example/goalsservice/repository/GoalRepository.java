package com.example.goalsservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.goalsservice.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

}

