package shop.style.catalog.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.style.catalog.DTO.Form.ProductFormDTO;
import shop.style.catalog.DTO.ProductDTO;
import shop.style.catalog.Entity.Category;
import shop.style.catalog.Entity.Product;
import shop.style.catalog.Exception.ResourceIsNotActive;
import shop.style.catalog.Exception.ResourceNotFoundException;
import shop.style.catalog.Repository.CategoryRepository;
import shop.style.catalog.Repository.ProductRepository;
import shop.style.catalog.Service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDTO save(ProductFormDTO body) {
        List<String> category_ids = body.getCategory_ids();
        List<Category> listCategories = new ArrayList<>();

        for (String id : category_ids) {
            Optional<Category> category = categoryRepository.findById(id);
            if (category.isEmpty()) {
                throw new ResourceNotFoundException("Category is not present");
            }
            if (!category.get().getActive()) {
                throw new ResourceIsNotActive("Category is not active");
            }
            listCategories.add(category.get());
        }

        Product product = modelMapper.map(body, Product.class);
        Product savedProduct = this.productRepository.save(product);

        for (Category category : listCategories) {
            category.getProducts().add(savedProduct);
            categoryRepository.save(category);
        }
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();

        List<ProductDTO> productsDto = products.stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        productsDto.forEach(product -> {
            product.getVariations().forEach(variation -> {
                variation.setProduct_id(product.getId());
            });
        });
        return productsDto;
    }

    @Override
    public ProductDTO searchProductByID(String id) {
        Optional<Product> product = this.productRepository.findById(id);

        if (product.isPresent()) {
            return modelMapper.map(product.get(), ProductDTO.class);
        }
        throw new ResourceNotFoundException("ID " + id);
    }

    @Override
    public ProductDTO updateProduct(String id, ProductFormDTO body) {
       Optional<Product> product = this.productRepository.findById(id);

       if (product.isPresent()) {
           Product updatedProduct = modelMapper.map(body, Product.class);
           updatedProduct.setId(product.get().getId());
           this.productRepository.save(updatedProduct);

           return modelMapper.map(updatedProduct, ProductDTO.class);
       }
       throw new ResourceNotFoundException("ID " + id);
    }

    @Override
    public void deleteProduct(String id) {
        Optional<Product> product = this.productRepository.findById(id);

        if (!product.isPresent()) {
            throw new ResourceNotFoundException("ID " + id);
        }
        this.productRepository.deleteById(product.get().getId());
    }
}
