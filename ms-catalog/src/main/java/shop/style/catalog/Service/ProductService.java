package shop.style.catalog.Service;

import shop.style.catalog.DTO.Form.ProductFormDTO;
import shop.style.catalog.DTO.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO save(ProductFormDTO body);

    List<ProductDTO> getAllProduct();

    ProductDTO searchProductByID(String id);

    ProductDTO updateProduct(String id, ProductFormDTO body);

    void deleteProduct(String id);
}
