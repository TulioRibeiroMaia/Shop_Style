package shop.style.history.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String name;

    private String description;

    private String color;

    private String size;

    private BigDecimal price;

    private Integer quantity;
}
