package com.estudou.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to create or update a user with essential details such as first name,
 * last name, email, username, and password.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

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
