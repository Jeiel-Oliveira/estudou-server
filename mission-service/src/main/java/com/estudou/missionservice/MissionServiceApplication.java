package com.estudou.missionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MissionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MissionServiceApplication.class, args);
	}

}