package shop.style.catalog.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.style.catalog.DTO.Form.ProductFormDTO;
import shop.style.catalog.DTO.ProductDTO;
import shop.style.catalog.Entity.Product;
import shop.style.catalog.Exception.ResourceNotFoundException;
import shop.style.catalog.Repository.ProductRepository;
import shop.style.catalog.Service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO save(ProductFormDTO body) {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    @Override
    public ProductDTO searchProduct(Long id) {
        Optional<Product> product = this.productRepository.findById(id);

        if (product.isPresent()) {
            return modelMapper.map(product.get(), ProductDTO.class);
        }

        throw new ResourceNotFoundException("ID" + id);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductFormDTO body) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
