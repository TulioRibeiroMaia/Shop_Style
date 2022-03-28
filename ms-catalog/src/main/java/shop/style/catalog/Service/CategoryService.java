package shop.style.catalog.Service;

import shop.style.catalog.DTO.CategoryDTO;
import shop.style.catalog.DTO.Form.CategoryFormDTO;
import shop.style.catalog.DTO.ProductDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO saveCategory(CategoryFormDTO body);

    List<CategoryDTO> getAllCategory();

    CategoryDTO updateCategory(String  id, CategoryFormDTO body);

    void deleteCategory(String id);

    List<ProductDTO> listAllProductsByCategory(String id);

}
