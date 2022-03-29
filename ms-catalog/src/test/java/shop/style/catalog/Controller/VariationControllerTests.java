package shop.style.catalog.Controller;

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
import shop.style.catalog.DTO.Form.VariationFormDTO;
import shop.style.catalog.Enum.EnumSize;
import shop.style.catalog.Service.Impl.VariationServiceImpl;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class VariationControllerTests {

    @Autowired
    private VariationController variationController;

    @MockBean
    private VariationServiceImpl variationServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(variationController)
                .build();
    }

    @Test
    @DisplayName("Deveria cadastrar uma nova variação")
    void shouldSaveANewVariation() throws Exception {
        VariationFormDTO variationFormDTO = new VariationFormDTO("Cinza",
                EnumSize.G,
                new BigDecimal(229.99),
                15,
                "8769809098809");

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/variations")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(variationFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Deveria retornar Bad Request ao tentar salvar uma variação com campo inválido ")
    void shouldNotSaveAVariationWithInvalidField() throws Exception {
        VariationFormDTO variationFormDTO =  new VariationFormDTO("Z",
                EnumSize.G,
                new BigDecimal(229.99),
                15,
                "8769809098809");

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/variations")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(variationFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deveria retornar Bad Request ao tentar salvar uma variação com campo vazio")
    void shouldNotSaveAVariationWithEmptyField() throws Exception {
        VariationFormDTO variationFormDTO = new VariationFormDTO("",
                EnumSize.G,
                new BigDecimal(229.99),
                15,
                "8769809098809");

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/variations")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(variationFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deveria listar a variação pelo seu id retornar status ok")
    void shouldListAVariantionByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/variations/8769809098809"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Não deveria listar a variação se não informar o id")
    void shouldNotListAVariantionIfDontInformID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/variations/"))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("Deveria atualizar os dados de uma variação pelo seu ID(cor alterada)")
    void shouldUpdateAVariationByID() throws Exception {
        VariationFormDTO variationFormDTO = new VariationFormDTO("Rosa",
                EnumSize.G,
                new BigDecimal(229.99),
                15,
                "8769809098809");
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/variations/8769809098809")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(variationFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Não deveria atualizar os dados de uma variação se um campo estiver vazio")
    void shouldNotUpdateAVariationIfEmptyField() throws Exception {
        VariationFormDTO variationFormDTO = new VariationFormDTO("",
                EnumSize.G,
                new BigDecimal(229.99),
                15,
                "8769809098809");
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/variations/8769809098809")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(variationFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Não deveria atualizar os dados de uma variação se um capo for inválido")
    void shouldNotUpdateAVariationWithInvalidField() throws Exception {
        VariationFormDTO variationFormDTO = new VariationFormDTO("Z",
                EnumSize.G,
                new BigDecimal(229.99),
                15,
                "8769809098809");
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/variations/8769809098809")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(variationFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Não deveria atualizar os dados de uma variação se não informar o ID")
    void shouldNotUpdateAVariationIfDontInformID() throws Exception {
        VariationFormDTO variationFormDTO = new VariationFormDTO("Z",
                EnumSize.G,
                new BigDecimal(229.99),
                15,
                "8769809098809");
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/variations/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(variationFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("Deveria deletar uma variação pelo seu ID")
    void shouldDeleteAProductByID() throws Exception {
        VariationFormDTO variationFormDTO = new VariationFormDTO("Cinza",
                EnumSize.G,
                new BigDecimal(229.99),
                15,
                "8769809098809");
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/variations/8769809098809")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(variationFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("Não deveria deletar uma variação se não informar o ID")
    void shouldNotDeleteAVariationIfDontInformID() throws Exception {
        VariationFormDTO variationFormDTO = new VariationFormDTO("Cinza",
                EnumSize.G,
                new BigDecimal(229.99),
                15,
                "8769809098809");
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/variations/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(variationFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }
}
