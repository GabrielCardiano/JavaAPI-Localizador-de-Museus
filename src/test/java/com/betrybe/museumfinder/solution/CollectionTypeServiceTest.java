package com.betrybe.museumfinder.solution;


import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@DisplayName("Teste classe CollectionTypeService")
public class CollectionTypeServiceTest {

  @Autowired
  private CollectionTypeService service;

  @Test
  @DisplayName("Testa método colectionTypeCount")
  void testCollectionsTypeCount() {
    CollectionTypeCount collection = service.countByCollectionTypes("história");
    assertEquals(387, collection.count());
  }

}
