package shop.style.bffshop.DTO.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartFormDTO {

    private String variant_id;

    private Integer quantity;
}
