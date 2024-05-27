package com.estudou.userservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldFindAll() throws Exception {
    // List<User> users = Arrays.asList(null);
    mockMvc.perform(get("/api/user")).andExpect(status().isOk());
  }
}