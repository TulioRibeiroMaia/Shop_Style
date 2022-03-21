package shop.style.catalog.Service.Impl;

import shop.style.catalog.DTO.CategoryDTO;
import shop.style.catalog.DTO.Form.CategoryFormDTO;
import shop.style.catalog.Entity.Category;
import shop.style.catalog.Entity.Product;
import shop.style.catalog.Service.CategoryService;

import java.util.List;

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
    public CategoryDTO updateCategory(Long id, CategoryFormDTO body) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public List<Product> findProductByCategory(Long id) {
        return null;
    }
}
