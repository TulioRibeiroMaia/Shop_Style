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
import shop.style.catalog.DTO.Form.ProductFormDTO;
import shop.style.catalog.Service.Impl.ProductServiceImpl;

import java.util.Collections;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ProductControllerTests {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductServiceImpl productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .build();
    }

    @Test
    @DisplayName("Deveria cadastrar uma novo produto")
    void shouldSaveANewProduct() throws Exception {
        ProductFormDTO productFormDTO = new ProductFormDTO("teste",
                "Jaquetas de Couro",
                true,
                Collections.singletonList("id"));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(productFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Deveria retornar Bad Request ao tentar salvar um produto com campo inválido ")
    void shouldNotSaveAProductWithInvalidField() throws Exception {
        ProductFormDTO productFormDTO =  new ProductFormDTO("XX",
                "Calças Slim",
                true,
                Collections.singletonList("id"));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(productFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deveria retornar Bad Request ao tentar salvar um produto com campo vazio")
    void shouldNotSaveProductWithEmptyField() throws Exception {
        ProductFormDTO productFormDTO = new ProductFormDTO("Jaquetas",
                "",
                true,
                Collections.singletonList("id"));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(productFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Não deveria cadastrar um produto caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotSaveAProductIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/product"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Deveria listar todos produtos e retornar status ok")
    void shouldListAllProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/products"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Não deveria deletar uma variação caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotListAllProductIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/product"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Deveria listar o produto pelo seu id retornar status ok")
    void shouldListAProductByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/products/8769809098809"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Não deveria listar o produto se não informar o ID")
    void shouldNotListAProductifNotInformID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/products/"))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("Não deveria listar um produto caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotListAProductIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/product"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Deveria atualizar os dados de um producto pelo seu ID(nome e descrição alterados)")
    void shouldUpdateAProductsByID() throws Exception {
        ProductFormDTO productFormDTO = new ProductFormDTO("Casacos",
                "Casacos de moletom",
                true,
                Collections.singletonList("id"));
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/products/8769809098809")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(productFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Não deveria atualizar os dados de uma variação se um campo estiver vazio")
    void shouldNotUpdateAVariationIfEmptyField() throws Exception {
        ProductFormDTO productFormDTO = new ProductFormDTO(" ",
                "Casacos de moletom",
                true,
                Collections.singletonList("id"));
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/products/8769809098809")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(productFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Não deveria atualizar os dados de um produto se um campo for inválido")
    void shouldNotUpdateAProductWithInvalidField() throws Exception {
        ProductFormDTO productFormDTO = new ProductFormDTO("XX",
                "Casacos de moletom",
                true,
                Collections.singletonList("id"));
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/products/8769809098809")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(productFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Não deveria atualizar os dados de um produto se não informar o ID")
    void shouldNotUpdateAProductIfDontInformID() throws Exception {
        ProductFormDTO productFormDTO = new ProductFormDTO("Casacos",
                "Casacos de moletom",
                true,
                Collections.singletonList("id"));
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/products/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(productFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("Não deveria deletar uma variação caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotUpdateAProductIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/product"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Deveria deletar um produto pelo seu ID")
    void shouldDeleteAProductByID() throws Exception {
        ProductFormDTO productFormDTO = new ProductFormDTO("Casacos",
                "Casacos de moletom",
                true,
                Collections.singletonList("id"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/products/8769809098809")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(productFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("Não deveria deletar um produto se não informar o ID")
    void shouldNotDeleteAProductIfDontInformID() throws Exception {
        ProductFormDTO productFormDTO = new ProductFormDTO();
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/products/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(productFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("Não deveria deletar um produto caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotDeleteAProductIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/product"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
