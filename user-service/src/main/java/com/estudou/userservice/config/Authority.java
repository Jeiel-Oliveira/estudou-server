package com.estudou.userservice.config;

/**
 * Authority class to centralize user permissions.
 * <p>
 * This roles must configured in the keycloak
 * </p>
 */
public class Authority {
  public static final String ADMIN = "hasAuthority('admin')";
}
