package shop.style.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.style.DTO.CustomerDTO;
import shop.style.DTO.FormDTO.CustomerFormDTO;
import shop.style.Enums.Sex;
import shop.style.Exception.ResourceNotFoundException;
import shop.style.Service.CustomerService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class CustomerServiceImplTest {

    @Autowired
    CustomerService customerService;

    @Test
    @DisplayName("Deve salvar um cliente")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldSaveACustomerTest() {
        CustomerFormDTO customerFormDTO = new CustomerFormDTO("Ronaldo",
                "Fenômeno",
                Sex.MASCULINO,
                "103.428.990-07",
                LocalDate.of(1990,10,20),
                "ronaldo.fernomeno@email.com",
                "12345678",
                true);

        CustomerDTO savedCustomer = this.customerService.saveCustomer(customerFormDTO);

        assertNotNull(savedCustomer);
        assertEquals(1L, savedCustomer.getId());
        assertEquals("103.428.990-07", savedCustomer.getCPF());
    }

    @Test
    @DisplayName("Deve retornar um cliente pelo ID informado")
    void shouldSearchACostumerByIDTest() {
        CustomerDTO customerDTO = this.customerService.findCustomerById(1L);

        assertNotNull(customerDTO);
        assertEquals(1L, customerDTO.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção Resource Not Found ao procurar por um ID que não existe")
    void shouldSearchACustomerByInvalidIDTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.customerService.findCustomerById(90L));
    }

    @Test
    @DisplayName("Deve atualizar os dados de um cliente pelo ID informado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateCustomerByIDTest() {
        CustomerFormDTO customerFormDTO = new CustomerFormDTO("Ronaldo",
                "Nazário",
                Sex.MASCULINO,
                "103.428.990-07",
                LocalDate.of(1990,10,20),
                "ronaldo.fernomeno@email.com",
                "12345678",
                true);

        CustomerDTO customerDTO = this.customerService.updateCustomer(1L, customerFormDTO);
        assertNotNull(customerDTO);
        assertEquals(1L, customerDTO.getId());
        assertEquals("Nazário", customerDTO.getLastName());
    }

    @Test
    @DisplayName("Deve lançar uma exceção Resource Not Found ao tentar atualizar por um ID não encontrado")
    void shouldNotUpdateACustomerByInvalidIDTest() {
        assertThrows(ResourceNotFoundException.class, () -> this.customerService.updateCustomer(90L, null));
    }
}
