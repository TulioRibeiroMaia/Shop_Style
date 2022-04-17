package shop.style.checkout.Service;

import shop.style.checkout.DTO.Form.PurchaseFormDTO;
import shop.style.checkout.DTO.PurchaseDTO;

public interface PurchaseService {

    void savePurchase(PurchaseFormDTO body);
}
