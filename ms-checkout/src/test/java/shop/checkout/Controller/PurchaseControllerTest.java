package shop.checkout.Controller;

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
import shop.checkout.Controller.PurchaseController;
import shop.checkout.DTO.Form.PurchaseFormDTO;
import shop.checkout.Service.Impl.PurchaseServiceImpl;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PurchaseControllerTest {

    @Autowired
    private PurchaseController purchaseController;

    @MockBean
    private PurchaseServiceImpl purchaseService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(purchaseController)
                .build();
    }

    @Test
    @DisplayName("Deveria cadastrar uma nova compra")
    void shouldSaveANewPurchase() throws Exception {
        PurchaseFormDTO purchaseFormDTO = new PurchaseFormDTO( 1L,
               1L ,
                new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/purchases")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(purchaseFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    //Falha ao passar o ID(Necessita de solu????o)
//    @Test
//    @DisplayName("Deveria retornar Bad Request a tentar salvar uma compra com campo inv??lido")
//    void savePurchaseWithInvalidField() throws Exception {
//        PurchaseFormDTO purchaseFormDTO = new PurchaseFormDTO( L,
//                1L,
//                new ArrayList<>());
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/v1/purchases")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(purchaseFormDTO)))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//    }

    @Test
    @DisplayName("Deveria retornar Bad Request ao tentar salvar uma compra com campo vazio")
    void savePurchaseWithEmptyField() throws Exception {
        PurchaseFormDTO purchaseFormDTO = new PurchaseFormDTO( null,
                1L ,
                new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/purchases")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(purchaseFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("N??o deveria cadatrar uma compra caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotSaveAPurchaseIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/purchase"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
