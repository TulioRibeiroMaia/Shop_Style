package shop.style.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.style.DTO.PaymentDTO;

@FeignClient("checkout")
public interface CheckoutClient {

    @GetMapping("/v1/payments/{id}")
    PaymentDTO findById(@PathVariable Long id);
}
