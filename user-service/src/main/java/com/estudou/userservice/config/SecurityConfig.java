package com.estudou.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Security configuration class for setting up method security and HTTP
 * security.
 *
 * <p>
 * This class configures method-level security with annotations and sets up a
 * security filter chain to handle HTTP requests. It adds a custom filter
 * {@link PreAuthenticatedUserRoleHeaderFilter} before the
 * {@link BasicAuthenticationFilter} in the security filter chain.
 * </p>
 */
@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig {

  /**
   * Configures the security filter chain.
   *
   * <p>
   * This method sets up the HTTP security configuration by adding the
   * {@link PreAuthenticatedUserRoleHeaderFilter} before the
   * {@link BasicAuthenticationFilter} and permitting all requests.
   * </p>
   *
   * @param http the {@link HttpSecurity} to modify
   * @return the {@link SecurityFilterChain} that represents the security
   *         configuration
   * @throws Exception if an error occurs during configuration
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    PreAuthenticatedUserRoleHeaderFilter preAuthenticatedUserFilter = new PreAuthenticatedUserRoleHeaderFilter();

    return http.addFilterBefore(preAuthenticatedUserFilter, BasicAuthenticationFilter.class)
        .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll()).build();
  }
}