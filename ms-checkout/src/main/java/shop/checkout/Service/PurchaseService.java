package shop.checkout.Service;

import shop.checkout.DTO.Form.PurchaseFormDTO;

public interface PurchaseService {

    void savePurchase(PurchaseFormDTO body);
}
