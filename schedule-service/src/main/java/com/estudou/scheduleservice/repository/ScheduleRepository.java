package com.estudou.scheduleservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.estudou.scheduleservice.model.Schedule;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {}
