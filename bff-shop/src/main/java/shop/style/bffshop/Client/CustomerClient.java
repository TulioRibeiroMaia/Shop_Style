package shop.style.bffshop.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import shop.style.bffshop.DTO.CustomerDTO;
import shop.style.bffshop.DTO.Form.CustomerFormDTO;

@FeignClient("customer")
public interface CustomerClient {

    @GetMapping("/v1/users/{id}")
    CustomerDTO findCustomerByID(@PathVariable Long id);


    @PutMapping("/v1/users/{id}")
    CustomerDTO updateCustomer (@PathVariable Long id, @RequestBody CustomerFormDTO customerFormDTO);

    @PostMapping("/v1/users")
    CustomerDTO saveCustomer(@RequestBody CustomerFormDTO customerFormDTO);

}
