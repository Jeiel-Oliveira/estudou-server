package com.estudou.userservice.service;

import java.util.ArrayList;
import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.estudou.userservice.config.KeycloakSecurityConfig;
import com.estudou.userservice.dto.UserRequest;
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
   * Retrieves a list of all users. It connects to Keycloak, retrieves user
   * representations, and maps them to {@link User} objects.
   *
   * @return a list of {@link User} objects representing all users
   */
  public List<User> findAll() {
    Keycloak keycloak = keycloakConfig.getKeycloakInstance();

    List<UserRepresentation> userRepresentations = keycloak.realm(realmName).users().list();

    return userRepresentations.stream().map(this::mapUser).toList();
  }

  /**
   * Creates a new user in the Keycloak realm.
   *
   * <p>
   * This method maps the provided {@code User} object to a
   * {@code UserRepresentation} object and then uses the Keycloak API to create
   * the user in the specified realm. After the user is created, it maps the
   * {@code UserRepresentation} back to a {@code User} object and returns it.</p>
   *
   * @param user the {@code User} object containing the details of the user to be
   *             created. This parameter must not be {@code null}.
   *
   * @return the {@code User} object representing the newly created user, mapped
   *         from the {@code UserRepresentation} used for the creation process.
   */
  public User create(UserRequest user) {
    UserRepresentation userRepresentation = mapUserRepresentation(user);

    Keycloak keycloak = keycloakConfig.getKeycloakInstance();
    keycloak.realm(realmName).users().create(userRepresentation);

    return mapUser(userRepresentation);
  }

  private User mapUser(UserRepresentation userRepresentation) {
    User user = new User();

    user.setEmail(userRepresentation.getEmail());
    user.setFirstName(userRepresentation.getFirstName());
    user.setLastName(userRepresentation.getLastName());
    user.setUsername(userRepresentation.getUsername());

    return user;
  }

  private UserRepresentation mapUserRepresentation(UserRequest user) {
    UserRepresentation userRepresentation = new UserRepresentation();

    userRepresentation.setUsername(user.getUsername());
    userRepresentation.setFirstName(user.getFirstName());
    userRepresentation.setLastName(user.getLastName());
    userRepresentation.setEmail(user.getEmail());
    userRepresentation.setEnabled(true);
    userRepresentation.setEmailVerified(true);

    List<CredentialRepresentation> credentials = new ArrayList<>();
    CredentialRepresentation credential = new CredentialRepresentation();

    credential.setTemporary(false);
    credential.setValue(user.getPassword());
    credentials.add(credential);
    userRepresentation.setCredentials(credentials);

    return userRepresentation;
  }

}
