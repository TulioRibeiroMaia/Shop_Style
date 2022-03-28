package shop.style.catalog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import shop.style.catalog.Entity.Product;
import shop.style.catalog.Entity.Variation;
import shop.style.catalog.Enum.EnumSize;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariationDTO {

    private String id;

    private String color;

    private EnumSize size;

    private BigDecimal price;

    private Integer quantity;


}
