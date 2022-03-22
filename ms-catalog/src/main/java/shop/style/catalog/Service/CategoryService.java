package shop.style.catalog.Service;

import shop.style.catalog.DTO.CategoryDTO;
import shop.style.catalog.DTO.Form.CategoryFormDTO;
import shop.style.catalog.Entity.Category;
import shop.style.catalog.Entity.Product;

import java.util.List;

public interface CategoryService {

    CategoryDTO saveCategory(CategoryFormDTO body);

    List<Category> getCategory();

    CategoryDTO updateCategory(String  id, CategoryFormDTO body);

    void deleteCategory(String id);

    List<Product> findProductByCategory(String  id);

}
