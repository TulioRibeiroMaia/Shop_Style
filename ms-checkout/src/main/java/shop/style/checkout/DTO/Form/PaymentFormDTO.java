package shop.style.checkout.DTO.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.style.checkout.Enums.PaymentType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentFormDTO {

    @NotNull
    private PaymentType type;

    @NotNull
    private BigDecimal discount;

    @NotNull
    private Boolean status;
}
