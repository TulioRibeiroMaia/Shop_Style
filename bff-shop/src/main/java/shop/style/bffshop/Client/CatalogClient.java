package shop.style.bffshop.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.style.bffshop.DTO.ProductDTO;

import java.util.List;

@FeignClient("catalog")
public interface CatalogClient {

    @GetMapping("/v1/products/{id}")
    ProductDTO searchProductById(@PathVariable String id);

    @GetMapping("/v1/categories/{id}/products")
    List<ProductDTO> getCategoryProduct(@PathVariable String id);
}
