package com.brokengate.Project.Estudou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProjectEstudouApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectEstudouApplication.class, args);
	}

}
