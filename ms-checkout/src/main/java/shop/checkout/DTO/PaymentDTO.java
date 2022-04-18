package shop.checkout.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.checkout.Enums.PaymentType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;

    private PaymentType type;

    private BigDecimal discount;

    private Boolean status;
}
