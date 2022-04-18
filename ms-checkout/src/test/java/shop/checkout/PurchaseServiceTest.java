package shop.checkout;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.checkout.Service.PurchaseService;

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
