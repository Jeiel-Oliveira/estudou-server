package com.estudou.userservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estudou.userservice.exception.ResponseAdvice;
import com.estudou.userservice.model.User;
import com.estudou.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseAdvice<List<User>> findAll(@RequestHeader("Authorization") String token) {
    List<User> users = userService.findAll();

    ResponseAdvice<List<User>> responseAdvice = new ResponseAdvice<List<User>>(
        HttpStatus.OK,
        "Users found successfully.",
        users
      );

    return responseAdvice;
  }

}
