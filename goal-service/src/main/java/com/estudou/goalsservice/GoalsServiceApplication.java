package com.estudou.goalsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main application class for the Goals Service. This class initializes and runs
 * the Goals Service application.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GoalsServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(GoalsServiceApplication.class, args);
  }
}
