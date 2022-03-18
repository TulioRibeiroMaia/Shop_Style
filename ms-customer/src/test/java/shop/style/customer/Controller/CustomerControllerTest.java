package shop.style.customer.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
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
import shop.style.customer.DTO.FormDTO.CustomerFormDTO;
import shop.style.customer.Enums.Sex;
import shop.style.customer.Service.Impl.CustomerServiceImpl;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerServiceImpl customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .build();
    }

    @Test
    @DisplayName("Deveria cadastrar um novo cliente")
    void shouldSaveANewCustomer() throws Exception {
        CustomerFormDTO customerFormDTO = new CustomerFormDTO("Ronaldo",
                "Fenômeno",
                Sex.MASCULINO,
                "103.428.990-07",
                LocalDate.now(),
                "ronaldo.fernomeno@email.com",
                "12345678",
                true);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customerFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Deveria retornar Bad Request a tentar salvar cliente com campo inválido (CPF inválido")
    void saveCustomerWithInvalidField() throws Exception {
        CustomerFormDTO customerFormDTO =  new CustomerFormDTO("Ronaldo",
                "Fenômeno",
                Sex.MASCULINO,
                "000.000.000-00",
                LocalDate.now(),
                "ronaldo.fernomeno@email.com",
                "12345678",
                true);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customerFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deveria retornar Bad Request ao tentar salvar cliente com campo vazio")
    void saveCustomerWithEmptyField() throws Exception {
        CustomerFormDTO customerFormDTO = new CustomerFormDTO( "","Fenômeno",
                Sex.MASCULINO,
                "000.000.000-00",
                LocalDate.now(),
                "ronaldo.fernomeno@email.com",
                "12345678",
                true);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customerFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deveria listar um cliente com o ID informado e retornar status ok")
    void shouldListACustomerByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deveria atualizar os dados de um cliente pelo seu ID(email alterado)")
    void shouldUpdateACustomerByID() throws Exception {
        CustomerFormDTO customerFormDTO = new CustomerFormDTO("Ronaldo",
                "Fenômeno",
                Sex.MASCULINO,
                "103.428.990-07",
                LocalDate.now(),
                "ronaldo@email.com",
                "12345678",
                true);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/users/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customerFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
