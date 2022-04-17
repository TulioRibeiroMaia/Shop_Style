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
import shop.style.catalog.DTO.Form.CategoryFormDTO;
import shop.style.catalog.Service.Impl.CategoryServiceImpl;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryControllerTests {

    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryServiceImpl categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .build();
    }

    @Test
    @DisplayName("Deveria cadastrar uma nova categoria")
    void shouldSaveANewCategory() throws Exception {
        CategoryFormDTO categoryFormDTO = new CategoryFormDTO("Bermudas", true);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Deveria retornar Bad Request ao tentar salvar uma categoria com campo inválido (Nome sem caracteres mínimos)")
    void shouldNotSaveACategoryWithInvalidField() throws Exception {
        CategoryFormDTO categoryFormDTO =  new CategoryFormDTO( "TT" , true);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Deveria retornar Bad Request ao tentar salvar uma categoria com campo vazio")
    void shouldNotSaveCategoryWithEmptyField() throws Exception {
        CategoryFormDTO customerFormDTO = new CategoryFormDTO(" ", true);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customerFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Não deveria cadastrar uma categoria caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotSaveACategoryIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/categorie"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Deveria listar todas categorias e retornar status ok")
    void shouldListAllCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/categories"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Não deveria listar as categorias caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotListACategoryIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/categorie"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


//    @Test
//    @DisplayName("Deveria listar os produtos de uma determinada categoria")
//    void shouldListAllProductsToACategory() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("v1/categories/8769809098809/products"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }

    @Test
    @DisplayName("Deveria atualizar os dados de uma categoria pelo seu ID(nome alterado)")
    void shouldUpdateACategoryByID() throws Exception {
        CategoryFormDTO categoryFormDTO = new CategoryFormDTO("Casacos", true);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/categories/8769809098809")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Não deveria atualizar os dados de uma categoria se um campo estiver vazio")
    void shouldNotUpdateACategoryIfEmptyField() throws Exception {
        CategoryFormDTO categoryFormDTO = new CategoryFormDTO("", true);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/categories/8769809098809")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Não deveria atualizar os dados de uma categoria se um campo for inválido")
    void shouldNotUpdateACategoryWithInvalidField() throws Exception {
        CategoryFormDTO categoryFormDTO = new CategoryFormDTO("XX", true);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/categories/8769809098809")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Não deveria atualizar os dados de uma categoria se não informar o ID")
    void shouldNotUpdateACategoryIfDontInformID() throws Exception {
        CategoryFormDTO categoryFormDTO = new CategoryFormDTO("Casacos", true);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/categories/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("Não deveria atualizar uma categoria caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotUpdateACategoryIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/categorie"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Deveria deletar uma categoria pelo seu ID")
    void shouldDeleteACategoryByID() throws Exception {
        CategoryFormDTO categoryFormDTO = new CategoryFormDTO("Casacos", true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/categories/8769809098809")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("Não deveria deletar uma categoria se não informar o ID")
    void shouldNotDeleteACategoryIfDontInformID() throws Exception {
        CategoryFormDTO categoryFormDTO = new CategoryFormDTO();

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/categories/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryFormDTO)))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("Não deveria deletar uma categoria caso tenha um erro na url e retornar NOT_FOUND")
    void shouldNotDeleteACategoryIfUrlIsWrong() throws Exception  {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/categorie"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
