package com.estudou.scheduleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main class to bootstrap the Schedule Service application. This class
 * configures and starts the Spring Boot application, enabling service
 * discovery.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ScheduleServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(ScheduleServiceApplication.class, args);
  }
}
