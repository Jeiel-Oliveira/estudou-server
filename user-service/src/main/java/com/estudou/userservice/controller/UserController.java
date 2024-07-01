package com.estudou.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estudou.userservice.config.Authority;
import com.estudou.userservice.dto.UserRequest;
import com.estudou.userservice.exception.ResponseAdvice;
import com.estudou.userservice.exception.UserNotFoundException;
import com.estudou.userservice.model.User;
import com.estudou.userservice.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST controller for handling user-related requests.
 *
 * <p>
 * This controller provides endpoints for managing users. It includes a method
 * to retrieve all users. The {@link UserService} is used to interact with the
 * user data.
 * </p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private final UserService userService;

  /**
   * Retrieves all users.
   *
   * @return a {@link ResponseAdvice} object containing the status, message, and
   *         list of users
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(Authority.ADMIN)
  public ResponseAdvice<List<User>> findAll() {
    List<User> users = userService.findAll();

    ResponseAdvice<List<User>> responseAdvice = new ResponseAdvice<List<User>>(HttpStatus.OK,
        "Users found successfully.", users);

    return responseAdvice;
  }

  /**
   * Create a new user.
   *
   * @return a {@link ResponseAdvice} object containing the status, message, and
   *         list of users
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize(Authority.ADMIN)
  public ResponseAdvice<User> create(@Valid @RequestBody UserRequest userRequest) {
    User user = userService.create(userRequest);

    ResponseAdvice<User> responseAdvice = new ResponseAdvice<User>(HttpStatus.CREATED,
        "Users found successfully.", user);

    return responseAdvice;
  }

  /**
 * Updates the user information for a given user ID with the details provided in the {@link UserRequest}.
 *
 * @param userId the ID of the user to update, extracted from the URL path. This must not be {@code null} or empty.
 * @param userRequest the {@link UserRequest} object containing the new user details. This must be a valid, non-null request body.
 * @return a {@link ResponseAdvice} object containing the HTTP status, a success message, and the updated {@link User} object.
 */
  @PutMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(Authority.ADMIN)
  public ResponseAdvice<User> update(@PathVariable(value = "userId") String userId, @Valid @RequestBody UserRequest userRequest) {
    User user = userService.update(userId, userRequest);

    ResponseAdvice<User> responseAdvice = new ResponseAdvice<User>(HttpStatus.OK,
        "User update successfully.", user);

    return responseAdvice;
  }

  /**
   * Retrieves a user by their ID and returns a standardized response.
   *
   * @param userId the unique identifier of the user, extracted from the URL.
   * @return a {@link ResponseAdvice} object containing the user details, an OK
   *         status, and a success message.
   * @throws UserNotFoundException if the user with the specified {@code userId}
   */
  @GetMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseAdvice<User> findById(@PathVariable(value = "userId") String userId) {

    User user = userService.findById(userId);

    ResponseAdvice<User> responseAdvice = new ResponseAdvice<User>(HttpStatus.OK,
        "User found successfully.", user);

    return responseAdvice;
  }

  /**
   * Deletes a user identified by the given userId from the system.
   *
   * @param userId the unique identifier of the user, extracted from the URL.
   * @return a {@link ResponseAdvice} object containing the user details, an OK
   *         status, and a success message.
   * @throws UserNotFoundException if the user with the specified {@code userId}
   */
  @DeleteMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseAdvice<Boolean> delete(@PathVariable(value = "userId") String userId) {

    Boolean hasDeleted = userService.delete(userId);

    ResponseAdvice<Boolean> responseAdvice = new ResponseAdvice<Boolean>(HttpStatus.OK,
        "User deleted with the id " + userId, hasDeleted);

    return responseAdvice;
  }
}
