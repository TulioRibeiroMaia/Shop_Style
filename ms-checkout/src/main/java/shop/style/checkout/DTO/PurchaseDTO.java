package shop.style.checkout.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.style.checkout.DTO.Form.CartFormDTO;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {

    private Long user_id;

    private Long payment_id;

    private List<CartFormDTO> cart = new ArrayList<>();
}
