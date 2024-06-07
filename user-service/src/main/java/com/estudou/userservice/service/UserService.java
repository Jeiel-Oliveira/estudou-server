package com.estudou.userservice.service;

import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.estudou.userservice.config.Authority;
import com.estudou.userservice.config.KeycloakSecurityConfig;
import com.estudou.userservice.model.User;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing users. This service provides methods to interact
 * with Keycloak and perform operations related to users.
 */
@Service
@RequiredArgsConstructor
public class UserService {

  @Autowired
  private KeycloakSecurityConfig keycloakConfig;

  @Value("${keycloak-realm}")
  private String realmName;

  /**
   * Retrieves a list of all users. This method is secured and can only be
   * accessed by users with the 'admin' authority. It connects to Keycloak,
   * retrieves user representations, and maps them to {@link User} objects.
   *
   * @return a list of {@link User} objects representing all users
   */
  @PreAuthorize(Authority.ADMIN)
  public List<User> findAll() {
    Keycloak keycloak = keycloakConfig.getKeycloakInstance();

    List<UserRepresentation> userRepresentations = keycloak.realm(realmName).users().list();

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
