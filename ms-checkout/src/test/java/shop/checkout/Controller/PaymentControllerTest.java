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
import shop.checkout.Controller.PaymentController;
import shop.checkout.Service.Impl.PaymentServiceImpl;
import shop.checkout.Enums.PaymentType;
import shop.checkout.DTO.Form.PaymentFormDTO;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PaymentControllerTest {

    @Autowired
    private PaymentController paymentController;

    @MockBean
    private PaymentServiceImpl paymentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController)
                .build();
    }

    @Test
    @DisplayName("Deveria cadastrar um novo pagamento")
    void shouldSaveANewPayment() throws Exception {
        PaymentFormDTO paymentFormDTO = new PaymentFormDTO( PaymentType.PIX,
                new BigDecimal("10"),
                true);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/payments")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(paymentFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Deveria retornar Bad Request a tentar salvar um pagamento com campo inválido")
    void savePaymentWithInvalidField() throws Exception {
        PaymentFormDTO paymentFormDTO = new PaymentFormDTO( PaymentType.CASH,
                null,
                false);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/payments")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(paymentFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deveria retornar Bad Request ao tentar salvar pagamento com campo vazio")
    void savePaymentWithEmptyField() throws Exception {
        PaymentFormDTO paymentFormDTO = new PaymentFormDTO(  null,
                new BigDecimal("20"),
                true);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/payments")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(paymentFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Não deveria cadastrar um pagamento caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotSaveAPaymentIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/payment"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Deveria listar todos pagamentos cadastrados retornar status ok")
    void shouldListAllPayments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/payments"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Não deveria listar os pagamentos caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotListALlPaymentsIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.get(
                        "/v1/payment"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Deveria listar um pagamento com o ID informado e retornar status ok")
    void shouldListAPaymentsByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/payments/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Não deveria listar um pagamento casa o ID informado esteja errado e retornar status BAD_REQUEST")
    void shouldNotListAPaymentsByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/payments/X"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Não deveria listar um pagamento caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotListAPaymentIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/payment"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Deveria atualizar os dados de um pagamento pelo seu ID e retornar status OK")
    void shouldUpdateAPaymentByID() throws Exception {
        PaymentFormDTO paymentFormDTO = new PaymentFormDTO( PaymentType.CREDIT_CARD,
                new BigDecimal("3"),
                true);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/payments/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(paymentFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Não deveria atualizar os dados de um pagamento caso não encontre o ID e retornar status NOT_FOUND")
    void shouldNotUpdateAPaymentByID() throws Exception {
        PaymentFormDTO paymentFormDTO = new PaymentFormDTO( PaymentType.CREDIT_CARD,
                new BigDecimal("3"),
                true);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/payments/X")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(paymentFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Não deveria atualizar um pagamento caso tenha um erro na url e retornar METHOD_NOT_ALLOWED")
    void shouldNotUpdateAPaymentIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/payments"))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("Deveria deletar um pagamento pelo seu ID")
    void shouldDeleteAPaymentByID() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/payments/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("Não deveria deletar um pagamento caso não encontre o ID e retornar status NOT_FOUND")
    void shouldNotDeleteAPaymentByID() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/payments/X"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Não deveria deletar um pagamento caso tenha um erro na url e retornar METHOD_NOT_ALLOWED")
    void shouldNotDeleteAPaymentIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/payments"))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }
}
