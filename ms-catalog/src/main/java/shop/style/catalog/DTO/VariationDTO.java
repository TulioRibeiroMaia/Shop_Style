package shop.style.catalog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.style.catalog.Enum.EnumSize;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariationDTO {

    private String id;

    private String color;

    private EnumSize size;

    private BigDecimal price;

    private Integer quantity;

    private String product_id;
}
