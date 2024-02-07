package com.betrybe.museumfinder.solution;

import static com.betrybe.museumfinder.evaluation.utils.TestHelpers.createMockMuseum;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * The type Controller test.
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Req 09")
public class ControllerTest {

  @MockBean
  MuseumServiceInterface serviceInterface;
  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("09 - Rota GET /museums/{id} implementada")
  void testGetMuseumById() throws Exception {
    Museum museum = createMockMuseum(10L);
    Mockito.when(serviceInterface.getMuseum(any()))
        .thenReturn(museum);

    mockMvc.perform(
            get("/museums/999")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value(museum.getName()))
        .andExpect(jsonPath("$.address").value(museum.getAddress()))
        .andExpect(jsonPath("$.description").value(museum.getDescription()))
        .andExpect(jsonPath("$.collectionType").value(museum.getCollectionType()))
        .andExpect(jsonPath("$.subject").value(museum.getSubject()))
        .andExpect(jsonPath("$.url").value(museum.getUrl()))
        .andExpect(jsonPath("$.coordinate.latitude").value(museum.getCoordinate().latitude()))
        .andExpect(jsonPath("$.coordinate.longitude").value(museum.getCoordinate().longitude()));

    Mockito.verify(serviceInterface).getMuseum(any());

    Mockito.reset(serviceInterface);

  }
}
