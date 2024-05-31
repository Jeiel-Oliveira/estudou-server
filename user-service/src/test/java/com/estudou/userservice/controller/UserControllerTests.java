package com.estudou.userservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.estudou.userservice.model.User;
import com.estudou.userservice.service.UserService;

@WebMvcTest(UserController.class)
class UserControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  @WithMockUser(username = "admin", authorities = { "admin" })
  void shouldFindAll() throws Exception {

    List<User> users = Arrays.asList(generateUser(), generateUser());

    when(userService.findAll()).thenReturn(users);

    mockMvc.perform(get("/api/user").header("Authorization", "Bearer tokenizou"))
        .andExpect(status().isOk());
  }

  @Test
  void unauthorizedErrorWhenFindAll() throws Exception {
    mockMvc.perform(get("/api/user").header("Authorization", "Bearer tokenizou"))
        .andExpect(status().isUnauthorized());
  }

  private User generateUser() {
    User user = new User();

    user.setEmail("test@email.com");
    user.setFirstName("Test");
    user.setLastName("Testing");
    user.setUsername("test");

    return user;
  }
}