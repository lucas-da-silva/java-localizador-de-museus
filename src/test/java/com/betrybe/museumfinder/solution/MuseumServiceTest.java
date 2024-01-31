package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@DisplayName("Testando as regras de négocio da classe MuseumService")
public class MuseumServiceTest {

  @MockBean
  MuseumFakeDatabase database;

  @Test
  @DisplayName("Retorna o museu corretamento com base no id")
  void testGetMuseumWithValidId() {
    long id = 1;
    Optional<Museum> optionalMuseum = Optional.of(new Museum());
    Mockito.when(database.getMuseum(id)).thenReturn(optionalMuseum);
    MuseumService service = new MuseumService(database);
    Museum result = service.getMuseum(id);
    assertEquals(optionalMuseum.get(), result);
  }

  @Test
  @DisplayName("Lança uma exceção quando o museu não existe")
  void testGetMuseumThrowException() {
    long id = 120;
    Mockito.when(database.getMuseum(id)).thenReturn(Optional.empty());
    MuseumService service = new MuseumService(database);
    assertThrows(MuseumNotFoundException.class, () -> {
      service.getMuseum(id);
    });
  }
}
