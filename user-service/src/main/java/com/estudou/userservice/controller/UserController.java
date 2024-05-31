package com.estudou.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estudou.userservice.exception.ResponseAdvice;
import com.estudou.userservice.model.User;
import com.estudou.userservice.service.UserService;

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
  public ResponseAdvice<List<User>> findAll() {
    List<User> users = userService.findAll();

    ResponseAdvice<List<User>> responseAdvice = new ResponseAdvice<List<User>>(HttpStatus.OK,
        "Users found successfully.", users);

    return responseAdvice;
  }

}
