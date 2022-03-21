package shop.style.catalog.Service;

import shop.style.catalog.DTO.CategoryDTO;
import shop.style.catalog.DTO.Form.CategoryFormDTO;
import shop.style.catalog.Entity.Category;
import shop.style.catalog.Entity.Product;

import java.util.List;

public interface CategoryService {

    CategoryDTO saveCategory(CategoryFormDTO body);

    List<Category> getCategory();

    CategoryDTO updateCategory(Long id, CategoryFormDTO body);

    void deleteCategory(Long id);

    List<Product> findProductByCategory(Long id);

}
