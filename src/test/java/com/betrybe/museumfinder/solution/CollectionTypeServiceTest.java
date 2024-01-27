package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testando as regras de négocio da classe CollectionTypeService")
public class CollectionTypeServiceTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MuseumFakeDatabase database;

  @Test
  @DisplayName("Quando chamado com um tipo de coleção, retorna a contagem correta")
  void testOneCollectionType() {
    String collectionType = "hist";
    long totalCount = 387;
    CollectionTypeCount expectedCollectionTypeCount = new CollectionTypeCount(
        new String[]{collectionType},
        totalCount
    );
    Mockito.when(database.countByCollectionType(collectionType)).thenReturn(totalCount);

    CollectionTypeService service = new CollectionTypeService(database);
    CollectionTypeCount result = service.countByCollectionTypes(collectionType);

    assertArrayEquals(expectedCollectionTypeCount.collectionTypes(), result.collectionTypes());
    assertEquals(expectedCollectionTypeCount.count(), result.count());
    Mockito.verify(database).countByCollectionType(collectionType);
  }

  @Test
  @DisplayName("Quando chamado com mais de um tipo de coleção, retorna a contagem correta")
  void testMultipleCollectionType() {
    String typesList = "hist,imag";
    String[] collectionType = typesList.split(",");
    long totalCount = 492;
    CollectionTypeCount expectedCollectionTypeCount = new CollectionTypeCount(
        collectionType,
        totalCount
    );

    Mockito.when(database.countByCollectionType(collectionType[0]))
        .thenReturn(387L);
    Mockito.when(database.countByCollectionType(collectionType[1]))
        .thenReturn(105L);

    CollectionTypeService service = new CollectionTypeService(database);
    CollectionTypeCount result = service.countByCollectionTypes(typesList);

    assertArrayEquals(expectedCollectionTypeCount.collectionTypes(), result.collectionTypes());
    assertEquals(expectedCollectionTypeCount.count(), result.count());
    Mockito.verify(database).countByCollectionType(collectionType[0]);
    Mockito.verify(database).countByCollectionType(collectionType[1]);
  }

  @Test
  @DisplayName("Retorna a contagem com valor igual a 0 quando não encontrado o tipo de coleção")
  void testNotFoundCollectionType() {
    String collectionType = "a";
    long totalCount = 0;
    CollectionTypeCount expectedCollectionTypeCount = new CollectionTypeCount(
        new String[]{collectionType},
        totalCount
    );
    Mockito.when(database.countByCollectionType(collectionType)).thenReturn(totalCount);

    CollectionTypeService service = new CollectionTypeService(database);
    CollectionTypeCount result = service.countByCollectionTypes(collectionType);

    assertArrayEquals(expectedCollectionTypeCount.collectionTypes(), result.collectionTypes());
    assertEquals(expectedCollectionTypeCount.count(), result.count());
    Mockito.verify(database).countByCollectionType(collectionType);
  }
}
