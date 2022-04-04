package shop.style.checkout.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.style.checkout.DTO.CustomerActiveDTO;

@FeignClient("customer")
public interface CustomerClient {

    @GetMapping("/v1/users/{id}")
    CustomerActiveDTO findById(@PathVariable Long id);
}
