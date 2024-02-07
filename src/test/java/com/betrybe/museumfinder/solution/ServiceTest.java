package com.betrybe.museumfinder.solution;

import static com.betrybe.museumfinder.evaluation.utils.TestHelpers.createMockMuseum;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Req 09")
public class ServiceTest {

  @MockBean
  MuseumFakeDatabase fakeDatabase;

  @Autowired
  MuseumServiceInterface serviceInterface;

  @Test
  @DisplayName("09 - Método getMuseumById da classe service")
  void testGetMuseumById() {
    testGetMuseumByIdNotFoundException();
    testGetMuseumByIdOk();
  }

  private void testGetMuseumByIdNotFoundException() {
    Mockito.reset(fakeDatabase);

    Mockito.when(fakeDatabase.getMuseum(any()))
        .thenReturn(Optional.empty());

    testException(() -> serviceInterface
            .getMuseum(999L),
        "com.betrybe.museumfinder.exception.MuseumNotFoundException",
        "Classe de serviço deve lançar uma 'unchecked exception' se nenhum museu encontrado"
    );

    Mockito.verify(fakeDatabase)
        .getMuseum(999L);
  }

  private void testGetMuseumByIdOk() {
    Museum museum = createMockMuseum(10L);
    Mockito.when(fakeDatabase.getMuseum(any()))
        .thenReturn(Optional.of(museum));

    Museum returnedMuseum = serviceInterface.getMuseum(10L);
    Mockito.verify(fakeDatabase)
        .getMuseum(eq(museum.getId()));

    assertEquals(museum.getName(), returnedMuseum.getName());
  }

  private void testException(Executable executable, String expectedException, String message) {
    RuntimeException exception = assertThrows(
        RuntimeException.class,
        executable,
        message
    );
    assertEquals(
        expectedException,
        exception.getClass().getName()
    );
  }
}
