package com.estudou.scheduleservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.estudou.scheduleservice.model.Schedule;

/**
 * Repository interface for managing Schedule entities. This interface provides
 * methods to perform CRUD operations on Schedule entities in the database.
 */
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
}
