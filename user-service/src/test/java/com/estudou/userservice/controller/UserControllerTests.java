package com.estudou.userservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.estudou.userservice.model.User;
import com.estudou.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
class UserControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Autowired
  private ObjectMapper objectMapper;

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

  @Test
  @WithMockUser(username = "admin", authorities = { "admin" })
  void shouldCreate() throws Exception {
    User userRequest = generateUser();
    String userRequestString = objectMapper.writeValueAsString(userRequest);

    when(userService.create(any())).thenReturn(generateUser());

    mockMvc
        .perform(post("/api/user").header("Authorization", "Bearer tokenizou")
            .contentType(MediaType.APPLICATION_JSON).content(userRequestString).with(csrf()))
        .andExpect(status().isCreated());
  }

  @Test
  @WithMockUser(username = "admin", authorities = { "admin" })
  void shouldReturnInvalidFields() throws Exception {
    mockMvc
        .perform(
            post("/api/user").contentType(MediaType.APPLICATION_JSON).content("{}").with(csrf()))
        .andExpect(status().isBadRequest());
  }

  private User generateUser() {
    User user = new User();

    user.setEmail("test@email.com");
    user.setFirstName("Test");
    user.setLastName("Testing");
    user.setUsername("test");
    user.setPassword("password123");

    return user;
  }
}