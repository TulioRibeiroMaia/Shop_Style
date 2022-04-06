package shop.style.checkout.DTO.RabbitMessage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.style.checkout.DTO.VariationMessage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseMessage {

    private Long user_id;

    private Long payment_id;

    private BigDecimal priceTotal;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate localDate;

    private List<VariationMessage> variationMessages;

}
