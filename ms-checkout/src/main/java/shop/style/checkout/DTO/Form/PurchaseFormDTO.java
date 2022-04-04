package shop.style.checkout.DTO.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseFormDTO {

    @NotNull
    private Long user_id;

    @NotNull
    private Long payment_id;

    @NotNull
    private List<@Valid CartFormDTO> cart = new ArrayList<>();
}
