package com.betrybe.museumfinder.solution;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testando as rotas da classe CollectionTypeController")
public class CollectionTypeControllerTest {

  private final String URL = "/collections/count/";
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private CollectionTypeService service;

  @Test
  @DisplayName("Retorna corretamente a contagem de tipos de coleção")
  void testGetCollectionTypesCount() throws Exception {
    String typesList = "hist,imag";
    CollectionTypeCount collectionTypeCount = new CollectionTypeCount(typesList.split(","), 492);

    Mockito
        .when(service.countByCollectionTypes(typesList))
        .thenReturn(collectionTypeCount);
    ResultActions result = mockMvc.perform(get(URL + typesList));

    result.andExpect(status().isOk());
    Mockito.verify(service).countByCollectionTypes(typesList);
  }

  @Test
  @DisplayName("Com tipo de coleção inexistente, retorna status 404")
  void testNotFoundCollectionTypesCount() throws Exception {
    String typesList = "a";
    Mockito
        .when(service.countByCollectionTypes(typesList))
        .thenReturn(new CollectionTypeCount(typesList.split(""), 0));
    ResultActions result = mockMvc.perform(get(URL + typesList));

    result.andExpect(status().isNotFound());
    Mockito.verify(service).countByCollectionTypes(typesList);
  }

}
