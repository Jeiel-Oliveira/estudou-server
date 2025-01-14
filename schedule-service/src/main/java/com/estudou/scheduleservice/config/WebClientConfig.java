package com.estudou.scheduleservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for WebClient. This class configures the WebClient
 * builder to support load balancing.
 */
@Configuration
public class WebClientConfig {

  @LoadBalanced
  @Bean
  public WebClient.Builder webClientBuilder() {
    return WebClient.builder();
  }
}
