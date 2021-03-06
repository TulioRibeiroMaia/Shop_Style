package shop.style.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.style.DTO.VariationCustomerDTO;
import shop.style.DTO.ProductDTO;

@FeignClient("catalog")
public interface CatalogClient {

    @GetMapping("/v1/variations/{id}")
    VariationCustomerDTO searchVariation(@PathVariable String id);

    @GetMapping("/v1/products/{id}")
    ProductDTO searchProductById(@PathVariable String id);
}
