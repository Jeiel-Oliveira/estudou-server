package com.estudou.userservice.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakSecurityConfig {

  Keycloak keycloak;

  @Value("${keycloak-server-url}")
  private String serverUrl;

  @Value("${keycloak-realm}")
  public String realm;

  @Value("${keycloak-username}")
  private String username;

  @Value("${keycloak-password}")
  private String password;

  @Value("${keycloak-client-id}")
  private String clientId;

  @Value("${keycloak-client-secret}")
  private String clientSecret;

  public Keycloak getKeycloakInstance() {
    if (keycloak == null) {
      keycloak = KeycloakBuilder.builder().serverUrl(serverUrl).realm(realm)
          .grantType(OAuth2Constants.PASSWORD).clientId(clientId).username(username)
          .password(password)
          .clientSecret(clientSecret).build();
    }

    return keycloak;
  }
}