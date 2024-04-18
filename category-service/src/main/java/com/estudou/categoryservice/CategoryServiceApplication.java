package com.estudou.categoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main application class for the Category Service. This class initializes and
 * runs the Category Service application.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CategoryServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(CategoryServiceApplication.class, args);
  }
}
