package com.brokengate.categoryservice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;

import com.brokengate.categoryservice.dto.CategoryRequest;
import com.brokengate.categoryservice.model.Category;
import com.brokengate.categoryservice.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryServiceApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;
	
	static final MySQLContainer<?> mysqlContainer;

	@Autowired
	private CategoryRepository categoryRepository;

	static {
		mysqlContainer = new MySQLContainer<>("mysql:8.0.35");
		mysqlContainer.start();
	}

	@DynamicPropertySource
	static void configureTestProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mysqlContainer::getUsername);
		registry.add("spring.datasource.password", mysqlContainer::getPassword);
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
	}

	@Test
	void shouldCreateCategory() throws Exception {		
		CategoryRequest categoryRequest = getCategoryRequest();
		String categoryRequestString = objectMapper.writeValueAsString(categoryRequest);

		mockMvc.perform(
			MockMvcRequestBuilders.post("/api/category")
			.contentType(MediaType.APPLICATION_JSON)
			.content(categoryRequestString)
		).andExpect(status().isCreated());

		Assertions.assertTrue(categoryRepository.findAll().size() == 1);
	}	

	@Test
	void shouldReturnListOfCategories() throws Exception {
		Category category = getCategory();
		categoryRepository.save(category);
		categoryRepository.save(category);

		mockMvc.perform(
			MockMvcRequestBuilders.get("/api/category")
			.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk());

		Assertions.assertTrue(categoryRepository.findAll().size() == 2);
	}

	private CategoryRequest getCategoryRequest() {
		CategoryRequest category = new CategoryRequest();
		category.setName("Testando");
		category.setColor("Azul");
		return category;
	}

	private Category getCategory() {
		Category category = new Category();
		category.setColor("Blue");
		category.setName("James");

		return category;
	}

}
