package com.brokengate.Project.Estudou.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.brokengate.Project.Estudou.model.Schedule;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {

}
