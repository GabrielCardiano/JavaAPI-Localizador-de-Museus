package com.betrybe.museumfinder.solution;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * The type Collection type controller test.
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes da classe CollectionTypeController")
public class CollectionTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("1 - Testa se o método getCollectionTypesCount retorna o número de museus")
  void testCountMuseums() throws Exception {
    String url = "/collections/count/história";
    mockMvc.perform(get(url))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.collectionTypes[0]").value("história"))
        .andExpect(jsonPath("$.count").value(387));
  }

  @Test
  @DisplayName("2 - Testa se o método getCollectionTypesCount retorna NOT_FOUND")
  void testNotCountMuseums() throws Exception {
    String url = "/collections/count/typesListInexistente";
    mockMvc.perform(get(url))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.collectionTypes").doesNotExist())
        .andExpect(jsonPath("$.count").doesNotExist());
  }
}
