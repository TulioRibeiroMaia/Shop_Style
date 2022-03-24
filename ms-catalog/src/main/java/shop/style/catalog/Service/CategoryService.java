package shop.style.catalog.Service;

import shop.style.catalog.DTO.CategoryDTO;
import shop.style.catalog.DTO.Form.CategoryFormDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO saveCategory(CategoryFormDTO body);

    List<CategoryDTO> getCategory();

    CategoryDTO updateCategory(String  id, CategoryFormDTO body);

    void deleteCategory(String id);

    CategoryDTO listAllProductsByCategory(String id);
}
