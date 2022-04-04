package shop.style.checkout.Service;

import shop.style.checkout.DTO.Form.PurchaseFormDTO;

public interface PurchaseService {

    void savePurchase(PurchaseFormDTO body);
}
