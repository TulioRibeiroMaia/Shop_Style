package shop.style.checkout.DTO.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartFormDTO {

    @NotNull
    private String variant_id;

    @NotNull
    private Integer quantity;
}
