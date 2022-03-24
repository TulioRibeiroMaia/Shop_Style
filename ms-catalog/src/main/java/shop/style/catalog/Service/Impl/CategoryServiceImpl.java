package shop.style.catalog.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.style.catalog.DTO.CategoryDTO;
import shop.style.catalog.DTO.Form.CategoryFormDTO;
import shop.style.catalog.Entity.Category;
import shop.style.catalog.Exception.ResourceNotFoundException;
import shop.style.catalog.Repository.CategoryRepository;
import shop.style.catalog.Repository.ProductRepository;
import shop.style.catalog.Service.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO saveCategory(CategoryFormDTO body) {
        Category category = modelMapper.map(body, Category.class);
        Category savedCategory = this.categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getCategory() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(String id, CategoryFormDTO body) {
        Optional<Category> category = this.categoryRepository.findById(id);

        if (category.isPresent()) {
            Category updatedCategory = modelMapper.map(body, Category.class);
            updatedCategory.setId(category.get().getId());
            this.categoryRepository.save(updatedCategory);

            return modelMapper.map(updatedCategory, CategoryDTO.class);
        }
        throw new ResourceNotFoundException("ID " + id);
    }

    @Override
    public void deleteCategory(String id) {
        Optional<Category> category = this.categoryRepository.findById(id);

        if (!category.isPresent()) {
            throw new ResourceNotFoundException("ID " + id);
        }
        this.categoryRepository.deleteById(category.get().getId());
    }

    @Override
    public CategoryDTO listAllProductsByCategory(String id) {
        Optional<Category> categoryOptional = this.categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            return modelMapper.map(categoryOptional.get(), CategoryDTO.class);
        }
        throw new ResourceNotFoundException("ID" + id);
    }
}
//        if (!categoryOptional.isPresent()) {
//            throw new ResourceNotFoundException("ID " + id);
//        }
//        if (!productOptional.isPresent()) {
//            throw new ResourceNotFoundException("ID " + body.getProductID());
//        }
//
//        Category category = categoryOptional.get();
//        Product product = productOptional.get();
//
//        category.setProducts((List<Product>) product);
//        this.categoryRepository.save(category);
//
//        return modelMapper.map(category, CategoryDTO.class);



