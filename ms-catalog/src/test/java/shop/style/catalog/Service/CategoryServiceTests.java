package shop.style.catalog.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.style.catalog.DTO.CategoryDTO;
import shop.style.catalog.DTO.Form.CategoryFormDTO;
import shop.style.catalog.Exception.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoryServiceTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("Deve salvar uma categoria")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldSaveANewCategoryTest() {
        CategoryFormDTO categoryFormDTO = new CategoryFormDTO("Camisas",
                true);

        CategoryDTO savedCategory = this.categoryService.saveCategory(categoryFormDTO);

        assertNotNull(savedCategory);
        assertEquals("Camisas", savedCategory.getName());
    }

    @Test
    @DisplayName("Deve retornar uma categoria pelo ID informado")
    void shouldSearchAllCategories() {
        CategoryDTO categoryDTO = this.categoryService.getAllCategory().get(1);

        assertNotNull(categoryDTO);
        assertEquals("623dcde37ee97b2fb77a6046", categoryDTO.getId());
    }


    @Test
    @DisplayName("Deve atualizar os dados de uma categoria pelo ID informado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateCategoryByIDTest() {
        CategoryFormDTO categoryFormDTO = new CategoryFormDTO("Bermudas",
                true);

        CategoryDTO categoryDTO = this.categoryService.updateCategory("623dcde37ee97b2fb77a6046", categoryFormDTO);
        assertNotNull(categoryDTO);
        assertEquals("623dcde37ee97b2fb77a6046", categoryDTO.getId());
        assertEquals("Bermudas", categoryDTO.getName());
    }

    @Test
    @DisplayName("Deve lançar uma exceção Resource Not Found ao tentar atualizar por um ID não encontrado")
    void shouldNotUpdateACategoryByInvalidIDTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.categoryService.updateCategory("89", null));
    }
}
