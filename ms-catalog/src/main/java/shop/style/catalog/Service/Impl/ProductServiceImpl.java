package shop.style.catalog.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.style.catalog.DTO.Form.ProductFormDTO;
import shop.style.catalog.DTO.ProductDTO;
import shop.style.catalog.Repository.ProductRepository;
import shop.style.catalog.Service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public ProductDTO save(ProductFormDTO body) {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        return null;
    }

    @Override
    public ProductDTO searchProduct(Long id) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductFormDTO body) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
