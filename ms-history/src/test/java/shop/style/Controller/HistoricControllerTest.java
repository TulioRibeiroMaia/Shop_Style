package shop.style.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import shop.style.Controller.HistoricController;
import shop.style.Service.Impl.HistoricServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HistoricControllerTest {

    @Autowired
    private HistoricController historicController;

    @MockBean
    private HistoricServiceImpl historicService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(historicController)
                .build();
    }

    @Test
    @DisplayName("Deveria listar o histórico com o ID informado e retornar status ok")
    void shouldListAHistoricByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/historic/user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Não deveria listar o historico se o Id informado não existir e retornar status BAD_REQUEST")
    void shouldNotListAHistoricIfNotInformByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/historic/user/X"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Não deveria listar o historic caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotListAHistoricIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/historic/use"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
