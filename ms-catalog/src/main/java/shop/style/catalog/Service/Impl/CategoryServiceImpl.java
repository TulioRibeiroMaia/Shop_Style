package shop.style.catalog.Service.Impl;

import org.springframework.stereotype.Service;
import shop.style.catalog.DTO.CategoryDTO;
import shop.style.catalog.DTO.Form.CategoryFormDTO;
import shop.style.catalog.Entity.Category;
import shop.style.catalog.Entity.Product;
import shop.style.catalog.Service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public CategoryDTO saveCategory(CategoryFormDTO body) {
        return null;
    }

    @Override
    public List<Category> getCategory() {
        return null;
    }

    @Override
    public CategoryDTO updateCategory(String  id, CategoryFormDTO body) {
        return null;
    }

    @Override
    public void deleteCategory(String id) {

    }

    @Override
    public List<Product> findProductByCategory(String id) {
        return null;
    }
}
