package shop.style.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariationCustomerDTO {

    private String color;

    private String size;

    private BigDecimal price;

    private Integer quantity;

    private String product_id;

}