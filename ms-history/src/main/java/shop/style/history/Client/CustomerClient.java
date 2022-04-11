package shop.style.history.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.style.history.DTO.CustomerDTO;

@FeignClient("customer")
public interface CustomerClient {

    @GetMapping("/v1/users/{id}")
    CustomerDTO findCustomerById(@PathVariable Long id);
}
