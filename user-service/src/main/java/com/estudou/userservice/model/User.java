package com.estudou.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a user.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private String firstName;
  private String lastName;
  private String email;

  private String username;
  private String password;
}
