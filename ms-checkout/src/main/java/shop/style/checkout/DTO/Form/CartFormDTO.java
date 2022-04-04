package shop.style.checkout.DTO.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartFormDTO {

    @NotNull
    private Long variant_id;

    @NotNull
    private Long quantity;
}
