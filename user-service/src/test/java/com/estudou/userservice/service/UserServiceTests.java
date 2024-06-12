package com.estudou.userservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.estudou.userservice.config.KeycloakSecurityConfig;
import com.estudou.userservice.model.User;

/**
 * Tests for UserService.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

  @Mock
  private KeycloakSecurityConfig keycloakSecurityConfig;

  @Mock
  private Keycloak keycloak;

  @InjectMocks
  private UserService userService;

  @BeforeEach
  void setup() {
    Keycloak mockedKeycloak = mock(Keycloak.class);

    when(keycloakSecurityConfig.getKeycloakInstance()).thenReturn(mockedKeycloak);

    when(mockedKeycloak.realm(any())).thenReturn(mock(RealmResource.class));

    when(mockedKeycloak.realm(any()).users()).thenReturn(mock(UsersResource.class));

    keycloak = mockedKeycloak;
  }

  @Test
  void shouldFindAll() {
    List<UserRepresentation> userRepresentations = Arrays.asList(generateUserRepresentation(),
        generateUserRepresentation());

    when(keycloak.realm(any()).users().list())
        .thenReturn(Arrays.asList(generateUserRepresentation(), generateUserRepresentation()));

    List<User> usersResponse = userService.findAll();

    Assertions.assertEquals(userRepresentations.size(), usersResponse.size());
    Assertions.assertInstanceOf(User.class, usersResponse.get(0));
  }

  @Test
  void shouldCreate() {
    User userResponse = userService.create(generateUser());

    Assertions.assertEquals(userResponse.getEmail(), "test@email.com");
    Assertions.assertEquals(userResponse.getFirstName(), "Test");
    Assertions.assertEquals(userResponse.getLastName(), "Testing");

    Assertions.assertInstanceOf(User.class, userResponse);
  }

  private UserRepresentation generateUserRepresentation() {
    UserRepresentation userRepresentation = new UserRepresentation();

    userRepresentation.setEmail("test@email.com");
    userRepresentation.setFirstName("Test");
    userRepresentation.setLastName("Testing");
    userRepresentation.setUsername("test");

    return userRepresentation;
  }

  private User generateUser() {
    User user = new User();

    user.setEmail("test@email.com");
    user.setFirstName("Test");
    user.setLastName("Testing");
    user.setUsername("Test");

    return user;
  }
}
