package shop.style.checkout.ServiceTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.style.checkout.DTO.Form.CartFormDTO;
import shop.style.checkout.DTO.Form.PurchaseFormDTO;
import shop.style.checkout.DTO.PurchaseDTO;
import shop.style.checkout.Service.PurchaseService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PurchaseServiceTest {

    @Autowired
    private PurchaseService purchaseService;

//    @Test
//    @DisplayName("Deve salvar uma compra")
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
//    void shouldSaveAPurchaseTest() {
//        PurchaseFormDTO purchaseFormDTO = new PurchaseFormDTO( 1L,
//                1L,
//                Collections.singletonList(newCartFormDTO()));
//
//        PurchaseDTO savePurchase = this.purchaseService.savePurchase(purchaseFormDTO);
//
//        assertNotNull(savePurchase);
//        assertEquals(1L, savePurchase.getUser_id());
//        assertEquals(1L, savePurchase.getPayment_id());
//    }
//
//    private CartFormDTO newCartFormDTO() {
//        return new CartFormDTO( "66598767566757996" ,2);
//    }
}
