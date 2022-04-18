package shop.style.bffshop.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import shop.style.bffshop.DTO.Form.PurchaseFormDTO;
import shop.style.bffshop.DTO.PaymentDTO;

import java.util.List;

@FeignClient("checkout")
public interface CheckoutClient {

    @GetMapping("/v1/payments")
    List<PaymentDTO> findAllPayments();

    @GetMapping("v1/purchases")
    void createPurchase(@RequestBody PurchaseFormDTO purchaseFormDTO);
}
