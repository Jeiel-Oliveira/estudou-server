package com.estudou.userservice.service;

import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.estudou.userservice.config.KeycloakSecurityConfig;
import com.estudou.userservice.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  @Autowired
  private KeycloakSecurityConfig keycloakConfig;

  @Value("${keycloak-realm}")
  private String realm;

  @PreAuthorize("hasAuthority('admin')")
  public List<User> findAll() {
    Keycloak keycloak = keycloakConfig.getKeycloakInstance();

    List<UserRepresentation> userRepresentations = keycloak.realm(realm).users().list();

    return userRepresentations.stream().map(this::mapUser).toList();
  }

  private User mapUser(UserRepresentation userRepresentation) {
    User user = new User();

    user.setEmail(userRepresentation.getEmail());
    user.setFirstName(userRepresentation.getFirstName());
    user.setLastName(userRepresentation.getLastName());
    user.setUsername(userRepresentation.getUsername());

    return user;
  }

}
