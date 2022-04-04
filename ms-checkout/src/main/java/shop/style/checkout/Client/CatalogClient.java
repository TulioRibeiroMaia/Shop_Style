package shop.style.checkout.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.style.checkout.DTO.ProductActiveDTO;
import shop.style.checkout.DTO.VariationProductDTO;

@FeignClient("catalog")
public interface CatalogClient {

    @GetMapping("v1/variations/{id}")
    VariationProductDTO getById (@PathVariable String id);

    @GetMapping("v1/variations/{id}")
    ProductActiveDTO findById(String id);
}
