package shop.style.bffshop.DTO.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseFormDTO {

    private Long user_id;

    private Long payment_id;

    private List<CartFormDTO> cart;
}
