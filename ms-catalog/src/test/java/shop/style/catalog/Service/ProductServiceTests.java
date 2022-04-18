package shop.style.catalog.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.style.catalog.Exception.ResourceNotFoundException;
import shop.style.catalog.DTO.Form.ProductFormDTO;
import shop.style.catalog.DTO.ProductDTO;
import shop.style.catalog.Service.ProductService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("Deve salvar um produto")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldSaveAProductTest() {
        ProductFormDTO productFormDTO = new ProductFormDTO("Camisas",
                "Camisas de Time de Futebol",
                true,
                Collections.singletonList("625b19ce3a057b17cfd5ec7e"));

        ProductDTO saveProduct = this.productService.save(productFormDTO);

        assertNotNull(saveProduct);
        assertEquals("623dd728fa92d55004a41f8c", saveProduct.getId());
        assertEquals("Camisas", saveProduct.getName());
    }

    @Test
    @DisplayName("Deve retornar um produto pelo ID informado")
    void shouldSearchAProductByIDTest() {
        ProductDTO productDTO = this.productService.searchProductByID("623dd728fa92d55004a41f8c");

        assertNotNull(productDTO);
        assertEquals("623dd728fa92d55004a41f8c", productDTO.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao procurar por um ID que não existe")
    void shouldSearchAProductByInvalidIDTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.productService.searchProductByID("88"));
    }

    @Test
    @DisplayName("Deve atualizar os dados de um produto pelo ID informado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateProductByIDTest() {
        ProductFormDTO productFormDTO = new ProductFormDTO("Camisas de Futebol",
                "Camisa do Cruzeiro",
                true,
                Collections.singletonList("1"));

        ProductDTO productDTO = this.productService.updateProduct("623dd728fa92d55004a41f8c", productFormDTO);
        assertNotNull(productDTO);
        assertEquals("623dd728fa92d55004a41f8c", productDTO.getId());
        assertEquals("Camisas de Futebol", productDTO.getName());
    }

    @Test
    @DisplayName("Deve lançar uma exceção Resource Not Found ao tentar atualizar por um ID não encontrado")
    void shouldNotUpdateACustomerByInvalidIDTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.productService.updateProduct("90", null));
    }
}
