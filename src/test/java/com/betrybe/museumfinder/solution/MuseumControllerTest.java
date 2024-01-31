package com.betrybe.museumfinder.solution;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
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
@DisplayName("Testando as rotas da classe MuseumController")
public class MuseumControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  MuseumServiceInterface serviceInterface;
  
  @Test
  @DisplayName("Retorna corretamente o museu pelo id")
  void testGetMuseumById() throws Exception {
    long id = 1;
    Mockito.when(serviceInterface.getMuseum(id)).thenReturn(new Museum());
    ResultActions result = mockMvc.perform(get("/museums/" + 1));
    result.andExpect(status().isOk());
    Mockito.verify(serviceInterface).getMuseum(id);
  }
}
