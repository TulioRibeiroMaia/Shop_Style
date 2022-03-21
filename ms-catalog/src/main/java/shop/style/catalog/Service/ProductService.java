package shop.style.catalog.Service;

import shop.style.catalog.DTO.Form.ProductFormDTO;
import shop.style.catalog.DTO.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO save(ProductFormDTO body);

    List<ProductDTO> getAllProduct();

    ProductDTO searchProduct(Long id);

    ProductDTO updateProduct(Long id, ProductFormDTO body);

    void deleteProduct(Long id);
}
