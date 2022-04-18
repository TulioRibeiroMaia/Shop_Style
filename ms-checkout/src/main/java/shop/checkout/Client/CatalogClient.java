package shop.checkout.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.checkout.DTO.ProductActiveDTO;
import shop.checkout.DTO.VariationProductDTO;

@FeignClient("catalog")
public interface CatalogClient {

    @GetMapping("/v1/variations/{id}")
    VariationProductDTO searchById (@PathVariable String id);

    @GetMapping("/v1/products/{id}")
    ProductActiveDTO findById(@PathVariable String id);
}
