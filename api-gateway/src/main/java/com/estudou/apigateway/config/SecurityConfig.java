package com.estudou.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Configuration class for security settings in the application.
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  /**
   * Configures security settings for the application.
   *
   * @param serverHttpSecurity The ServerHttpSecurity object used to configure
   *                           security.
   * @return The configured SecurityWebFilterChain object.
   * @throws Exception If an error occurs during configuration.
   */
  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity)
      throws Exception {
    serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable).authorizeExchange(
        exchange -> exchange.pathMatchers("/eureka/**").permitAll().anyExchange().authenticated())
        .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()));

    return serverHttpSecurity.build();
  }
}