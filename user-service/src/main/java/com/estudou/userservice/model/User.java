package com.estudou.userservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @Email
  @NotBlank
  private String email;

  @Pattern(regexp = "([A-Za-z0-9])\\w+", flags = Pattern.Flag.MULTILINE, message = "username cannot include spaces or special characters.")
  private String username;

  @NotBlank
  private String password;
}
