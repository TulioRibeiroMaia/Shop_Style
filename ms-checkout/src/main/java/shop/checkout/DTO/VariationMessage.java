package shop.checkout.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariationMessage {

    private String variant_id;

    private Integer quantity;
}
