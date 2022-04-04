package shop.style.checkout.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariationProductDTO {

    private String id;

    private BigDecimal price;

    private Integer quantity;

    private String product_id;
}
