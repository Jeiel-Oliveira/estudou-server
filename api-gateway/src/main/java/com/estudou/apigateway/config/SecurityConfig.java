package com.estudou.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Configuration class for security settings in the application.
 */
@Configuration
@EnableWebFluxSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
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

    serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
        .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(jwt -> {
          jwt.jwtAuthenticationConverter(jwtAuthenticationConverter());
        })).authorizeExchange(authorize -> authorize.pathMatchers("/eureka/**").permitAll()
            .anyExchange().authenticated());

    return serverHttpSecurity.build();
  }

  /**
   * This method creates and configures a JwtAuthenticationConverter object, which
   * is used for converting JWT tokens into Spring Security Authentication
   * objects. This implementation is necessary to configure roles of Keycloak in
   * the Jwt Token
   *
   * @return JwtAuthenticationConverter An instance of JwtAuthenticationConverter
   *         configured for converting JWT tokens into Authentication objects.
   */
  @Bean
  public ReactiveJwtAuthenticationConverterAdapter jwtAuthenticationConverter() {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    JwtGrantedAuthoritiesConverter grantedConverter = new JwtGrantedAuthoritiesConverter();

    grantedConverter.setAuthorityPrefix(""); // Default "SCOPE_"
    grantedConverter.setAuthoritiesClaimName("roles"); // Default "scope" or "scp"
    converter.setJwtGrantedAuthoritiesConverter(grantedConverter);

    return new ReactiveJwtAuthenticationConverterAdapter(converter);
  }

}