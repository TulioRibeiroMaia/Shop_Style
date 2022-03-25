package shop.style.catalog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shop.style.catalog.DTO.CategoryDTO;
import shop.style.catalog.DTO.Form.CategoryFormDTO;
import shop.style.catalog.Service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO saveCategory(@RequestBody @Valid CategoryFormDTO body) {
        return categoryService.saveCategory(body);
    }

    @GetMapping
    public List<CategoryDTO> getCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}/products")
    public CategoryDTO getCategoryProduct(@PathVariable String id) {
        return (CategoryDTO) categoryService.listAllProductsByCategory(id);
    }

    @PutMapping("{id}")
    public CategoryDTO updateCategory(@PathVariable String id, @RequestBody @Valid CategoryFormDTO body) {
        return categoryService.updateCategory(id, body);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
    }
}
