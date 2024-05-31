package com.estudou.userservice.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration class for setting up Keycloak integration.
 *
 * <p>
 * This class is responsible for configuring and providing an instance of
 * Keycloak client to interact with the Keycloak server. The configuration
 * properties for connecting to the Keycloak server are injected using Spring's
 * {@code @Value} annotation.
 * </p>
 *
 * <p>
 * Properties required for configuration:
 * <ul>
 * <li>{@code keycloak-server-url} - The URL of the Keycloak server</li>
 * <li>{@code keycloak-realm} - The realm name in Keycloak</li>
 * <li>{@code keycloak-username} - The username for authentication</li>
 * <li>{@code keycloak-password} - The password for authentication</li>
 * <li>{@code keycloak-client-id} - The client ID registered in Keycloak</li>
 * <li>{@code keycloak-client-secret} - The client secret associated with the
 * client ID</li>
 * </ul>
 * </p>
 *
 * <p>
 * Configuration example in the application.properties
 * <ul>
 * <li>keycloak-realm=estudou-realm</li>
 * <li>keycloak-server-url=http://localhost:8080</li>
 * <li>keycloak-username=estudou-admin</li>
 * <li>keycloak-client-id=estudou-admin</li>
 * <li>keycloak-client-secret=GiaYkuEca8d47a9vOn9TAaDGENic4iJK</li>
 * <li>keycloak-password=NRWCN7I7LmDRC5h</li>
 * </ul>
 * </p>
 *
 * <p>
 * Example usage:
 *
 * <pre>
 * {@code
 * @Autowired
 * private KeycloakSecurityConfig keycloakSecurityConfig;
 *
 * public void someMethod() {
 *   Keycloak keycloak = keycloakSecurityConfig.getKeycloakInstance();
 *   // use the Keycloak instance
 * }
 * }
 * </pre>
 * </p>
 */
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

  /**
   * Returns an instance of Keycloak client configured with the provided
   * properties.
   *
   * <p>
   * If the Keycloak instance has not been initialized yet, this method will
   * create and configure a new instance using the provided server URL, realm,
   * username, password, client ID, and client secret.
   * </p>
   *
   * @return an instance of {@link Keycloak}
   */
  public Keycloak getKeycloakInstance() {
    if (keycloak == null) {
      keycloak = KeycloakBuilder.builder().serverUrl(serverUrl).realm(realm)
          .grantType(OAuth2Constants.PASSWORD).clientId(clientId).username(username)
          .password(password).clientSecret(clientSecret).build();
    }

    return keycloak;
  }
}