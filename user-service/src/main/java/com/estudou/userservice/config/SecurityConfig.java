package com.estudou.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    PreAuthenticatedUserRoleHeaderFilter preAuthenticatedUserRoleHeaderFilter = new PreAuthenticatedUserRoleHeaderFilter();

    return http
        .addFilterBefore(preAuthenticatedUserRoleHeaderFilter, BasicAuthenticationFilter.class)
        .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll()).build();
  }

}