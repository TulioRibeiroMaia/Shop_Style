package shop.checkout.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.checkout.Service.PaymentService;
import shop.checkout.Enums.PaymentType;
import shop.checkout.DTO.Form.PaymentFormDTO;
import shop.checkout.DTO.PaymentDTO;
import shop.checkout.Exception.ResourceNotFoundException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class PaymentServiceTest {


    @Autowired
    private PaymentService paymentService;

    @Test
    @DisplayName("Deve salvar um pagamento")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldSaveANewPaymentTest() {
        PaymentFormDTO paymentFormDTO = new PaymentFormDTO(PaymentType.CASH,
                new BigDecimal(5),
                true);

        PaymentDTO savedPayment = this.paymentService.savePayment(paymentFormDTO);

        assertNotNull(savedPayment);
        assertEquals(PaymentType.CASH, savedPayment.getType());
    }

    @Test
    @DisplayName("Deve retornar um pagamento pelo ID informado")
    void shouldListPaymentByID() {
        PaymentDTO paymentDTO = this.paymentService.findById(1L);

        assertNotNull(paymentDTO);
        assertEquals(1L, paymentDTO.getId());
    }


    @Test
    @DisplayName("Deve atualizar os dados de uma categoria pelo ID informado")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updatePaymentByID() {
        PaymentFormDTO paymentFormDTO = new PaymentFormDTO(PaymentType.DEBIT_CARD,
                new BigDecimal(14),
                true);

        PaymentDTO savedPayment = this.paymentService.updatePayment(1L, paymentFormDTO);
        assertNotNull(savedPayment);
        assertEquals(1L, savedPayment.getId());
        assertEquals( PaymentType.DEBIT_CARD, savedPayment.getType());
    }

    @Test
    @DisplayName("Deve lançar uma exceção Resource Not Found ao tentar atualizar por um ID não encontrado")
    void shouldNotUpdateAPaymentByInvalidID() {
        assertThrows(ResourceNotFoundException.class, () -> this.paymentService.updatePayment(100000000000L, null));
    }
}
