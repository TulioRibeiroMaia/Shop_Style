package shop.style.catalog.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.style.catalog.DTO.Form.VariationFormDTO;
import shop.style.catalog.DTO.VariationDTO;
import shop.style.catalog.Enum.EnumSize;
import shop.style.catalog.Exception.ResourceNotFoundException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class VariationServiceTests {

    @Autowired
    private VariationService variationService;

    @Test
    @DisplayName("Deve salvar uma nova variação")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldSaveANewVariationTest() {
        VariationFormDTO variationFormDTO = new VariationFormDTO("Blue",
                EnumSize.G,
                new BigDecimal(209.99),
                2,
                "625b19ce3a057b17cfd5ec7e");

        VariationDTO savedVariation = this.variationService.saveVariation(variationFormDTO);

        assertNotNull(savedVariation);
        assertEquals(EnumSize.G, savedVariation.getSize());
        assertEquals("Blue", savedVariation.getColor());
    }

    @Test
    @DisplayName("Deve retornar uma variação pelo ID informado")
    void shouldSearchAVariationByIDTest() {
        VariationDTO variationDTO = this.variationService.searchVariation("id");

        assertNotNull(variationDTO);
        assertEquals(EnumSize.GG, variationDTO.getSize());
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao procurar por um ID que não existe")
    void shouldSearchAVariationByInvalidIDTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.variationService.searchVariation("99"));
    }

    @Test
    @DisplayName("Deve atualizar os dados de uma variação pelo ID informado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateVariationByIDTest() {
        VariationFormDTO variationFormDTO = new VariationFormDTO(
                "Pink",
                EnumSize.G,
                new BigDecimal(179.99),
                2,
                "625b19ce3a057b17cfd5ec7e");

        VariationDTO variationDTO = this.variationService.searchVariation("625b1af30053c36abe7d63a4");
        assertNotNull(variationDTO);
        assertEquals("625b1af30053c36abe7d63a4", variationDTO.getId());
        assertEquals("Pink", variationDTO.getColor());
    }

    @Test
    @DisplayName("Deve lançar uma exceção Resource Not Found ao tentar atualizar por um ID não encontrado")
    void shouldNotUpdateACustomerByInvalidIDTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.variationService.updateVariation("80", null));
    }
}
