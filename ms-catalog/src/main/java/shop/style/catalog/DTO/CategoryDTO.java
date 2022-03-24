package shop.style.catalog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.style.catalog.Entity.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private String id;

    private String name;

    private Boolean active;

    private List<Product> products = new ArrayList<>();
}
